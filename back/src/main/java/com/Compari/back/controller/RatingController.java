package com.Compari.back.controller;

import com.Compari.back.service.RatingService;
import com.Compari.back.ResponseDto.RatingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RatingResponseDto>> getAllRatings() {
        List<RatingResponseDto> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponseDto> getRatingById(@PathVariable int id) {
        Optional<RatingResponseDto> rating = ratingService.getRatingById(id);
        return rating.map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product/{productId}/user/{userId}")
    public ResponseEntity<RatingResponseDto> createProductRating(@RequestBody RatingResponseDto ratingResponseDto,
                                                                 @PathVariable("productId") Long productId,
                                                                 @PathVariable("userId") Integer userId) {
        RatingResponseDto savedRating = ratingService.saveProductRating(ratingResponseDto, userId, productId);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @PostMapping("/store/{storeId}/user/{userId}")
    public ResponseEntity<RatingResponseDto> createStoreRating(@RequestBody RatingResponseDto ratingResponseDto,
                                                               @PathVariable("storeId") Integer storeId,
                                                               @PathVariable("userId") Integer userId) {
        RatingResponseDto savedRating = ratingService.saveStoreRating(ratingResponseDto, storeId, userId);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @PutMapping("/product/{productId}/user/{userId}")
    public ResponseEntity<RatingResponseDto> updateProductRating(@RequestBody RatingResponseDto ratingResponseDto,
                                                                 @PathVariable("productId") Long productId,
                                                                 @PathVariable("userId") Integer userId) {
        RatingResponseDto updatedRating = ratingService.updateProductRating(ratingResponseDto, userId, productId);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

    @PutMapping("/store/{storeId}/user/{userId}")
    public ResponseEntity<RatingResponseDto> updateStoreRating(@RequestBody RatingResponseDto ratingResponseDto,
                                                               @PathVariable("storeId") Integer storeId,
                                                               @PathVariable("userId") Integer userId) {
        RatingResponseDto updatedRating = ratingService.updateStoreRating(ratingResponseDto, storeId, userId);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
