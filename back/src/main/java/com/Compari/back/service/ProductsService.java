package com.Compari.back.service;

import com.Compari.back.entity.Products;
import com.Compari.back.ResponseDto.ProductsResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<ProductsResponseDto> getAllProducts();

    Optional<ProductsResponseDto> getProductById(long id);

    ProductsResponseDto saveProduct(ProductsResponseDto productsResponseDto);

    ProductsResponseDto updateProduct(ProductsResponseDto productsResponseDto);

    void deleteProduct(long id);

    Page<ProductsResponseDto> getProductsPaginated(int page, int size);

    Products getProductByNameAndPrice(String name, Double price);
}
