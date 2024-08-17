package com.FindMyPc.back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private String description;
    private String image;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<StoreProduct> storeProduct;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings;

    @ManyToMany(mappedBy = "products")
    @JsonManagedReference
    private List<Wishlist> wishlists;
}