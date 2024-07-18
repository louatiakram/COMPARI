package com.FindMyPc.back.controller;

import com.FindMyPc.back.entity.Wishlist;
import com.FindMyPc.back.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.saveWishlist(wishlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable int id, @RequestBody Wishlist wishlistDetails) {
        Optional<Wishlist> optionalWishlist = wishlistService.getWishlistById(id);

        if (optionalWishlist.isPresent()) {
            Wishlist wishlist = optionalWishlist.get();
            wishlist.setUser(wishlistDetails.getUser());
            wishlist.setProduct(wishlistDetails.getProduct());
            return ResponseEntity.ok(wishlistService.saveWishlist(wishlist));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable int id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);

        if (wishlist.isPresent()) {
            wishlistService.deleteWishlist(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
