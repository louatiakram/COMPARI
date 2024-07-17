package com.FindMyPc.back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Custom query methods
    Page<Product> findAll(Pageable pageable);
}
