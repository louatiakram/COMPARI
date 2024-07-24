package com.FindMyPc.back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float priceBD;
    private float priceAD;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonManagedReference
    private Store store;
}