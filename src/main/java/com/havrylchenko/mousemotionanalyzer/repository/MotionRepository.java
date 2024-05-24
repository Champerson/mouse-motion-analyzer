package com.havrylchenko.mousemotionanalyzer.repository;

import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotionRepository extends JpaRepository<MotionStorage, Long> {

    List<MotionStorage> findByUserId(Long userId);

}
