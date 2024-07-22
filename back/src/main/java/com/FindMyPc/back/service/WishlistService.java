package com.FindMyPc.back.service;

import com.FindMyPc.back.entity.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<Wishlist> getAllWishlists();

    Optional<Wishlist> getWishlistById(int id);

    Wishlist saveWishlist(Wishlist wishlist);
    
    Wishlist updateWishlist(Wishlist wishlist);

    void deleteWishlist(int id);
}
