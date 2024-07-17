package com.FindMyPc.back.repository;


import com.FindMyPc.back.entity.Affected;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectedRepository extends JpaRepository<Affected, Integer> {
}
