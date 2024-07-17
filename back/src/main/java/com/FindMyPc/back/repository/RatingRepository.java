package com.FindMyPc.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
