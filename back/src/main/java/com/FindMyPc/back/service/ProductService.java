package com.FindMyPc.back.service;

import com.FindMyPc.back.ResponseDto.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();

    Optional<ProductResponseDto> getProductById(long id);

    ProductResponseDto saveProduct(ProductResponseDto productResponseDto);

    ProductResponseDto updateProduct(ProductResponseDto productResponseDto);

    void deleteProduct(long id);

    Page<ProductResponseDto> getProductsPaginated(int page, int size);
}
