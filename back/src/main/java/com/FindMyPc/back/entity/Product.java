package com.FindMyPc.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

	public Product(int productID, String name, String description, String image, List<Affected> affected,
			List<Rating> ratings, List<Wishlist> wishlists) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.image = image;
		this.affected = affected;
		this.ratings = ratings;
		this.wishlists = wishlists;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Affected> getAffected() {
		return affected;
	}

	public void setAffected(List<Affected> affected) {
		this.affected = affected;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public int getProductID() {
		return productID;
	}
    
}
