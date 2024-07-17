package com.FindMyPc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
