package com.FindMyPc.back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store_product")
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float priceAD; // Price from the product
    private double priceBD; // Price from the scraping source

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products product;


    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonManagedReference
    private Store store;

    // Constructor without id
    public StoreProduct(float priceAD, double priceBD, Store store) {
        this.priceAD = priceAD;
        this.priceBD = priceBD;
        this.store = store;
    }
}