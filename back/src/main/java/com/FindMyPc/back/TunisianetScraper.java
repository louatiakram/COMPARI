package com.FindMyPc.back;

import com.FindMyPc.back.entity.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.FindMyPc.back.entity.Products;
import com.FindMyPc.back.entity.ProductsImgs;
import com.FindMyPc.back.entity.StoreProduct;
import com.FindMyPc.back.repository.ProductsRepository;
import com.FindMyPc.back.repository.StoreRepository;
import com.FindMyPc.back.repository.StoreProductRepository;
import com.FindMyPc.back.service.ProductsImgsService;
import com.FindMyPc.back.service.ProductsService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class TunisianetScraper implements ApplicationRunner {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsImgsService productsImgsService;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Keep the application running indefinitely
        //keepRunning();
        startScraping();
    }
    private void keepRunning() {
        // Keep the application running after scraping completes
        while (true) {
            try {
                Thread.sleep(1000);  // Sleep for a second, then repeat
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Handle interrupt
            }
        }
    }

    private void startScraping() {
        String baseUrl = "https://www.tunisianet.com.tn/301-pc-portable-tunisie";
        String url = baseUrl;

        while (url != null) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements productLinks = doc.select("h2.product-title > a");

                List<CompletableFuture<Void>> futures = productLinks.stream()
                        .map(linkElement -> {
                            String productDetailUrl = linkElement.attr("href");
                            return CompletableFuture.runAsync(() -> extractProductDetails(productDetailUrl));
                        })
                        .collect(Collectors.toList());

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

                Element nextPageElement = doc.select(".next").first();
                url = (nextPageElement != null && nextPageElement.tagName().equals("a"))
                        ? resolveNextPageUrl(baseUrl, nextPageElement.attr("href"))
                        : null;

            } catch (IOException e) {
                System.err.println("Error fetching page: " + url);
                e.printStackTrace();
                break; // Stop if there's a problem with fetching the page
            }
        }
    }

    private String resolveNextPageUrl(String baseUrl, String nextPageHref) {
        return nextPageHref.startsWith("http") ? nextPageHref : baseUrl + nextPageHref;
    }





    private Map<String, String> extractAttributes(Document productDoc) {
        Map<String, String> attributes = new HashMap<>();
        Elements details = productDoc.select(".data-sheet dt");
        for (Element detail : details) {
            String key = detail.text().trim();
            String value = detail.nextElementSibling().text().trim();
            attributes.put(key, value);
        }
        // Debug: print the attributes map
        System.out.println("Extracted attributes: " + attributes);
        return attributes;
    }



    private void extractProductDetails(String productDetailUrl) {
        try {
            Document productDoc = Jsoup.connect(productDetailUrl).get();
            String title = productDoc.select("h1[itemprop=name]").text();
            String[] components = title.split(" / ");
            String name = components[0].trim();

            Map<String, String> attributes = extractAttributes(productDoc);

            String priceText = productDoc.select("span[itemprop=price]").first().text();
            double priceBD = parsePrice(priceText);

            List<ProductsImgs> imgs = extractImages(productDoc, name);

            if (priceBD > 0) {
                // Check if product already exists in the database
                Products product = productsRepository.findByNameAndPrice(name, priceBD)
                        .stream()
                        .findFirst()
                        .orElseGet(() -> new Products(null, name, attributes.get("Processeur"), attributes.get("Réf processeur"),
                                attributes.get("Mémoire"), attributes.get("Disque Dur"), attributes.get("Carte Graphique"),
                                attributes.get("Réf Carte graphique"), attributes.get("Taille Ecran"), attributes.get("Type Ecran"),
                                attributes.get("Ecran Tactile"), attributes.get("Réseau"), attributes.get("Caméra"),
                                attributes.get("Garantie"), attributes.get("Fréquence de rafraîchissement"), attributes.get("Couleur"),
                                priceBD, new ArrayList<>()));

                // Update the product with new attributes
                product.setProcessor(attributes.get("Processeur"));
                product.setProcessorRef(attributes.get("Réf processeur"));
                product.setMemory(attributes.get("Mémoire"));
                product.setHardDrive(attributes.get("Disque Dur"));
                product.setGpu(attributes.get("Carte Graphique"));
                product.setGpuRef(attributes.get("Réf Carte graphique"));
                product.setScreenSize(attributes.get("Taille Ecran"));
                product.setScreenType(attributes.get("Type Ecran"));
                product.setTouchScreen(attributes.get("Ecran Tactile"));
                product.setNetwork(attributes.get("Réseau"));
                product.setCamera(attributes.get("Caméra"));
                product.setWarranty(attributes.get("Garantie"));
                product.setRefreshRate(attributes.get("Fréquence de rafraîchissement"));
                product.setColor(attributes.get("Couleur"));

                productsRepository.save(product);

                // Fetch the store from the database (assuming constant store ID of 1 for now)
                Store store = storeRepository.findById(1).orElseThrow(() -> new RuntimeException("Store not found"));

                // Save StoreProduct entity
                StoreProduct storeProduct = storeProductRepository.findByStoreIdAndProductIdAndPriceBD(1, product.getId(), priceBD);
                if (storeProduct == null) {
                    storeProduct = new StoreProduct((float) priceBD, priceBD, store);
                    storeProduct.setProduct(product);
                    storeProductRepository.save(storeProduct);
                }

                // Save images with a relation to the product
                Set<String> seenImageNames = new HashSet<>();
                List<ProductsImgs> uniqueImgs = new ArrayList<>();
                for (ProductsImgs img : imgs) {
                    if (!seenImageNames.contains(img.getImgName())) {
                        ProductsImgs existingImg = productsImgsService.findByNameAndProductId(img.getImgName(), product.getId());
                        if (existingImg == null) {
                            img.setProduct(product);
                            uniqueImgs.add(img);
                            seenImageNames.add(img.getImgName());
                        }
                    }
                }
                productsImgsService.saveAll(uniqueImgs);

                System.out.println("Saving product: " + name + " with price: " + priceBD);
            } else {
                System.err.println("Skipping product due to price <= 0");
            }
        } catch (IOException e) {
            System.err.println("Error fetching product details from: " + productDetailUrl);
            e.printStackTrace();
        }
    }




    private List<ProductsImgs> extractImages(Document productDoc, String productName) {
        List<ProductsImgs> imgs = new ArrayList<>();
        Elements imgElements = productDoc.select(".images-container img");

        // Create a sanitized product name for folder path
        String sanitizedProductName = sanitizeFileName(productName);
        Path imgFolderPath = Paths.get("C:\\Users\\ASUS\\Desktop\\A\\Website\\COMPARI-ADMIN\\Front_User\\src\\assets\\img", sanitizedProductName);
        if (!Files.exists(imgFolderPath)) {
            try {
                Files.createDirectories(imgFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Element imgElement : imgElements) {
            String imgUrl = imgElement.attr("src");
            String imgName = imgUrl.substring(imgUrl.lastIndexOf('/') + 1);
            Path imgPath = imgFolderPath.resolve(imgName);

            try (InputStream in = new URL(imgUrl).openStream()) {
                Files.copy(in, imgPath, StandardCopyOption.REPLACE_EXISTING);
                imgs.add(new ProductsImgs(imgName, null)); // Associate the product later
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }


    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[<>:\"/\\|?*]", "_");
    }

    private double parsePrice(String priceText) {
        try {
            String cleanedPrice = priceText.replaceAll("[^0-9,]", "").replace(",", ".");
            return Double.parseDouble(cleanedPrice);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
