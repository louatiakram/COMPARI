package com.FindMyPc.back.repository;

import com.FindMyPc.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
