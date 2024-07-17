package com.FindMyPc.back.service;

import com.FindMyPc.back.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    List<Rating> getAllRatings();

    Optional<Rating> getRatingById(int id);

    Rating saveRating(Rating rating);

    void deleteRating(int id);
}
