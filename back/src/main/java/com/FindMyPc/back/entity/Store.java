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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeID;

    private String name;
    private String location;
    private String websiteURL;
    private String logo;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<StoreProduct> storeProduct;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings;
}