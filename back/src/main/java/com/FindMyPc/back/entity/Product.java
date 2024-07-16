package com.FindMyPc.back.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public List<Affected> getAffected() {
        return affected;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }


}
