package com.FindMyPc.back.service;

import com.FindMyPc.back.ResponseDto.WishlistResponseDto;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<WishlistResponseDto> getAllWishlists();

    Optional<WishlistResponseDto> getWishlistById(int id);

    WishlistResponseDto saveToWishlist(WishlistResponseDto WishlistResponseDto);
    

    void deleteFromWishlist(int id);
}
