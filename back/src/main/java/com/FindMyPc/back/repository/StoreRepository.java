package com.FindMyPc.back.repository;

import com.FindMyPc.back.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
