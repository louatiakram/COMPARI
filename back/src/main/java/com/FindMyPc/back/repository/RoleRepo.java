package com.FindMyPc.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FindMyPc.back.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
   Role findByRole(String role);
}
