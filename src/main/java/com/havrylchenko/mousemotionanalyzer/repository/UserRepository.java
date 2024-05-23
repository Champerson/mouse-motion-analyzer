package com.havrylchenko.mousemotionanalyzer.repository;

import com.havrylchenko.mousemotionanalyzer.model.User;
import com.havrylchenko.mousemotionanalyzer.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAllByRole(UserRole role);

}
