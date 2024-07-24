package com.FindMyPc.back.serviceImpl;
import com.FindMyPc.back.ResponseDto.ProductResponseDto;
import com.FindMyPc.back.entity.Product;
import com.FindMyPc.back.repository.ProductRepository;
import com.FindMyPc.back.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponseDto> getProductById(int id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductResponseDto.class));
    }

    @Override
    public ProductResponseDto saveProduct(ProductResponseDto productResponseDto) {
        Product product = modelMapper.map(productResponseDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto updateProduct(ProductResponseDto productResponseDto) {
        if (productRepository.existsById(productResponseDto.getProductID())) {
            Product product = modelMapper.map(productResponseDto, Product.class);
            Product updatedProduct = productRepository.save(product);
            return modelMapper.map(updatedProduct, ProductResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        // Handle the case where the product does not exist
    }

    @Override
    public Page<ProductResponseDto> getProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> modelMapper.map(product, ProductResponseDto.class));
    }
}