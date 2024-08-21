package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.ResponseDto.ProductsResponseDto;
import com.FindMyPc.back.entity.Products;
import com.FindMyPc.back.repository.ProductsRepository;
import com.FindMyPc.back.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductsResponseDto> getAllProducts() {
        List<Products> products = productsRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductsResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductsResponseDto> getProductById(long id) {
        return productsRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductsResponseDto.class));
    }

    @Override
    public ProductsResponseDto saveProduct(ProductsResponseDto productsResponseDto) {
        Products products = modelMapper.map(productsResponseDto, Products.class);
        Products savedProducts = productsRepository.save(products);
        return modelMapper.map(savedProducts, ProductsResponseDto.class);
    }

    @Override
    public ProductsResponseDto updateProduct(ProductsResponseDto productsResponseDto) {
        if (productsRepository.existsById(productsResponseDto.getId())) {
            Products products = modelMapper.map(productsResponseDto, Products.class);
            Products updatedProducts = productsRepository.save(products);
            return modelMapper.map(updatedProducts, ProductsResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteProduct(long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        }
        // Handle the case where the product does not exist
    }

    @Override
    public Page<ProductsResponseDto> getProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> productPage = productsRepository.findAll(pageable);
        return productPage.map(product -> modelMapper.map(product, ProductsResponseDto.class));
    }

    @Override
    public Products getProductByNameAndPrice(String name, Double price) {
        return productsRepository.findByNameAndPrice(name, price).stream().findFirst().orElse(null);
    }
}