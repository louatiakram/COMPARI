package com.FindMyPc.back.controller;

import com.FindMyPc.back.ResponseDto.WishlistResponseDto;
import com.FindMyPc.back.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

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
        return wishlist.map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<WishlistResponseDto> createWishlist(@RequestBody WishlistResponseDto wishlistResponseDto) {
        WishlistResponseDto createdWishlist = wishlistService.saveWishlist(wishlistResponseDto);
        return new ResponseEntity<>(createdWishlist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable int id) {
        wishlistService.deleteWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
