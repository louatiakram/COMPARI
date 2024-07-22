package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.entity.Rating;
import com.FindMyPc.back.repository.RatingRepository;
import com.FindMyPc.back.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> getRatingById(int id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }
    @Override
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
