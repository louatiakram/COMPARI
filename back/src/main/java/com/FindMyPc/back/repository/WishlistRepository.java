package com.FindMyPc.back.repository;

import com.FindMyPc.back.entity.User;
import com.FindMyPc.back.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//WishlistRepository.java
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    Optional<Wishlist> findByUser(User user);
}
