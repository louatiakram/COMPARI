package com.FindMyPc.back.service;

import com.FindMyPc.back.ResponseDto.RatingResponseDto;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<RatingResponseDto> getAllRatings();

    Optional<RatingResponseDto> getRatingById(int id);

    
    RatingResponseDto saveProductRating(RatingResponseDto RatingResponseDto, Integer userId,Integer productID);
    
    RatingResponseDto saveStoreRating(RatingResponseDto RatingResponseDto,  Integer storeID, Integer userId);

    RatingResponseDto updateProductRating(RatingResponseDto RatingResponseDto, Integer userId,Integer productID);
    
    RatingResponseDto updateStoreRating(RatingResponseDto RatingResponseDto,  Integer storeID, Integer userId);


    void deleteRating(int id);
}
