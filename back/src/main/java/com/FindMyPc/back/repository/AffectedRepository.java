package com.FindMyPc.back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.FindMyPc.back.entity.Affected;

public interface AffectedRepository extends JpaRepository<Affected, Integer> {
}
