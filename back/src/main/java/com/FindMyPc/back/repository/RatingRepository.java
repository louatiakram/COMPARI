package com.FindMyPc.back.repository;


import com.FindMyPc.back.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
