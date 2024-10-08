package com.Compari.back.controller;

import com.Compari.back.service.WishlistService;
import com.Compari.back.ResponseDto.WishlistResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private static final Logger logger = Logger.getLogger(WishlistController.class.getName());

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishlistResponseDto>> getAllWishlists() {
        List<WishlistResponseDto> wishlists = wishlistService.getAllWishlists();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistResponseDto> getWishlistById(@PathVariable int id) {
        Optional<WishlistResponseDto> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/user/{userId}/products/{productId}")
    public ResponseEntity<WishlistResponseDto> saveToWishlist(
            @PathVariable Integer userId,
            @PathVariable Long productId) {

        WishlistResponseDto savedWishlist = wishlistService.saveToWishlist(userId, productId);
        return new ResponseEntity<>(savedWishlist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFromWishlist(@PathVariable int id) {
        wishlistService.deleteFromWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
