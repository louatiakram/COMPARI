package com.FindMyPc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
