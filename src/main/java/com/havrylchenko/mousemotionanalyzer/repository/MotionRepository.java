package com.havrylchenko.mousemotionanalyzer.repository;

import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import com.havrylchenko.mousemotionanalyzer.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotionRepository {

    List<MotionStorage> findByUser(User user);

}
