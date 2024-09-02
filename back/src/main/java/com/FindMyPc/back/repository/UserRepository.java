package com.FindMyPc.back.repository;

import com.FindMyPc.back.entity.Role;
import com.FindMyPc.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
    boolean existsByRole(Role role);
    Optional<User> findByRole(Role role); // Add this method to find users by role

    

    List<User> findByUsernameContaining(String username);
}
