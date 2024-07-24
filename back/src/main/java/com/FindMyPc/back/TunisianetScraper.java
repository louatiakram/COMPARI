package com.FindMyPc.back;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import com.FindMyPc.back.entity.Computer;
import com.FindMyPc.back.service.ComputerService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class TunisianetScraper implements ApplicationRunner {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private ConfigurableApplicationContext appContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startScraping();
        keepRunning();
    }

    private void startScraping() {
        String baseUrl = "https://www.tunisianet.com.tn/301-pc-portable-tunisie";
        try {
            String url = baseUrl;
            while (url != null) {
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
                if (nextPageElement != null && nextPageElement.tagName().equals("a")) {
                    url = nextPageElement.attr("href");
                    if (!url.startsWith("http")) {
                        url = baseUrl + url;
                    }
                } else {
                    url = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractProductDetails(String productDetailUrl) {
        try {
            Document productDoc = Jsoup.connect(productDetailUrl).get();
            String title = productDoc.select("h1[itemprop=name]").text();
            String[] components = title.split(" / ");
            String name = components[0].trim();

            String processor = "";
            String processorRef = "";
            String memory = "";
            String hardDrive = "";
            String gpu = "";
            String gpuRef = "";
            String screenSize = "";
            String screenType = "";
            String touchScreen = "";
            String network = "";
            String camera = "";
            String warranty = "";
            String refreshRate = "";
            String color = "";

            Elements details = productDoc.select(".data-sheet");
            for (Element detail : details.select("dt")) {
                String key = detail.text().trim();
                String value = detail.nextElementSibling().text().trim();

                switch (key) {
                    case "Système d'exploitation":
                    case "Processeur":
                        processor = value;
                        break;
                    case "Réf processeur":
                        processorRef = value;
                        break;
                    case "Mémoire":
                        memory = value;
                        break;
                    case "Disque Dur":
                        hardDrive = value;
                        break;
                    case "Carte Graphique":
                        gpu = value;
                        break;
                    case "Réf Carte graphique":
                        gpuRef = value;
                        break;
                    case "Taille Ecran":
                        screenSize = value;
                        break;
                    case "Type Ecran":
                        screenType = value;
                        break;
                    case "Ecran Tactile":
                        touchScreen = value;
                        break;
                    case "Réseau":
                        network = value;
                        break;
                    case "Caméra":
                        camera = value;
                        break;
                    case "Garantie":
                        warranty = value;
                        break;
                    case "Taux de Rafraîchissement":
                        refreshRate = value;
                        break;
                    case "Couleur":
                        color = value;
                        break;
                }
            }

            String priceText = productDoc.select("span[itemprop=price]").first().text();
            String cleanedPrice = priceText.replace("DT", "").replace(",", ".").replace(" ", "").trim();
            cleanedPrice = cleanedPrice.replaceAll("(\\.)(?=.*\\.)", ""); // Remove all dots except the last one

            Double price = null;
            try {
                price = Double.parseDouble(cleanedPrice);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing price: " + cleanedPrice);
            }

            if (name != null && price != null && price > 0) {
                Computer computer = new Computer(null, name, processor, processorRef, memory, hardDrive, gpu, gpuRef,
                        screenSize, screenType, touchScreen, network, camera, warranty, refreshRate, color, price);
                computerService.saveProduct(computer);
                System.out.println("Saving or updating computer: " + name + " with price: " + price);
            } else {
                System.err.println("Skipping product due to null name, price, or price <= 0");
            }
        } catch (IOException e) {
            System.err.println("Error fetching product details from: " + productDetailUrl);
            e.printStackTrace();
        }
    }

    private void keepRunning() {
        try {
            while (true) {
                Thread.sleep(60000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
