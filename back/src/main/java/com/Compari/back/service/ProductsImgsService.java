package com.Compari.back.service;

import com.Compari.back.repository.ProductsImgsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Compari.back.entity.ProductsImgs;

import java.util.List;

@Service
public class ProductsImgsService {

    @Autowired
    private ProductsImgsRepository productsImgsRepository;

    // Method to find an image by name and product ID
    public ProductsImgs findByNameAndProductId(String imgName, Long productId) {
        return productsImgsRepository.findByImgNameAndProductId(imgName, productId);
    }

    public void saveAll(List<ProductsImgs> productsImgs) {
        productsImgsRepository.saveAll(productsImgs);
    }
}
