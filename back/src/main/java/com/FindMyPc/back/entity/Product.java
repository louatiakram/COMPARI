package com.FindMyPc.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Affected> affected;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;

}
