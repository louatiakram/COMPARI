package com.FindMyPc.back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String processor;
    private String processorRef;
    private String memory;
    private String hardDrive;
    private String gpu;
    private String gpuRef;
    private String screenSize;
    private String screenType;
    private String touchScreen;
    private String network;
    private String camera;
    private String warranty;
    private String refreshRate;
    private String color;
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductsImgs> imgs = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<StoreProduct> storeProduct;


    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "products")
    @JsonManagedReference
    private List<Wishlist> wishlists;

    public Products(Long id, String name, String processor, String processorRef, String memory, String hardDrive, String gpu, String gpuRef, String screenSize, String screenType, String touchScreen, String network, String camera, String warranty, String refreshRate, String color, Double price, List<ProductsImgs> imgs) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.processorRef = processorRef;
        this.memory = memory;
        this.hardDrive = hardDrive;
        this.gpu = gpu;
        this.gpuRef = gpuRef;
        this.screenSize = screenSize;
        this.screenType = screenType;
        this.touchScreen = touchScreen;
        this.network = network;
        this.camera = camera;
        this.warranty = warranty;
        this.refreshRate = refreshRate;
        this.color = color;
        this.price = price;
        this.imgs = imgs;
    }
}