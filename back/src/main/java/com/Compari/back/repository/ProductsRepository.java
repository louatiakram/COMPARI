package com.Compari.back.repository;

import com.Compari.back.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Page<Products> findAll(Pageable pageable);
    List<Products> findByNameAndPrice(String name, Double price);

    List<Products> findByNameContaining(String name);

}
