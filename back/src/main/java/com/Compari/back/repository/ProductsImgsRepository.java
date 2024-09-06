package com.Compari.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Compari.back.entity.ProductsImgs;

public interface ProductsImgsRepository extends JpaRepository<ProductsImgs, Long> {

    ProductsImgs findByImgNameAndProductId(String imgName, Long productId);
}
