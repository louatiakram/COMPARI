package com.FindMyPc.back.repository;

import com.FindMyPc.back.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

    List<User> findByUsernameContaining(String username);
}
