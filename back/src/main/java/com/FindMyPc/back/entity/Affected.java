package com.FindMyPc.back.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Affected {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float priceBD;
    private float priceAD;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Product getProduct() {
        return product;
    }

    public Store getStore() {
        return store;
    }
}
