package com.Compari.back.service;

import com.Compari.back.ResponseDto.WishlistResponseDto;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<WishlistResponseDto> getAllWishlists();

    Optional<WishlistResponseDto> getWishlistById(int id);

    WishlistResponseDto saveToWishlist(Integer userId, Long productId);

    void deleteFromWishlist(int id);
}
	