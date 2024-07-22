package com.FindMyPc.back.service;

import com.FindMyPc.back.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);

    Product saveProduct(Product product);
    
    Product updateProduct(Product product);


    void deleteProduct(int id);

    Page<Product> getProductsPaginated(int page, int size);
}
