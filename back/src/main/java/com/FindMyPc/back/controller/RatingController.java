package com.FindMyPc.back.controller;

import com.FindMyPc.back.entity.Rating;
import com.FindMyPc.back.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable int id) {
        Optional<Rating> rating = ratingService.getRatingById(id);
        return rating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.saveRating(rating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable int id, @RequestBody Rating ratingDetails) {
        Optional<Rating> optionalRating = ratingService.getRatingById(id);

        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            rating.setContent(ratingDetails.getContent());
            rating.setStore(ratingDetails.getStore());
            rating.setProduct(ratingDetails.getProduct());
            rating.setUser(ratingDetails.getUser());
            return ResponseEntity.ok(ratingService.saveRating(rating));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable int id) {
        Optional<Rating> rating = ratingService.getRatingById(id);

        if (rating.isPresent()) {
            ratingService.deleteRating(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
