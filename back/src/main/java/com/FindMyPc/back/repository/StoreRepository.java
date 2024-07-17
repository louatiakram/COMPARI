package com.FindMyPc.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
