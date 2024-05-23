package com.havrylchenko.mousemotionanalyzer.service;

import com.havrylchenko.mousemotionanalyzer.listener.GlobalMouseListener;
import com.havrylchenko.mousemotionanalyzer.model.User;
import com.havrylchenko.mousemotionanalyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private GlobalMouseListener globalMouseListener;


    public void startMotionCapturing() {

    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
