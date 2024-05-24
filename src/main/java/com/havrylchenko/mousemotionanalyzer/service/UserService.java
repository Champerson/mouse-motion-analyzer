package com.havrylchenko.mousemotionanalyzer.service;

import com.havrylchenko.mousemotionanalyzer.listener.GlobalMouseListener;
import com.havrylchenko.mousemotionanalyzer.model.User;
import com.havrylchenko.mousemotionanalyzer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private GlobalMouseListener globalMouseListener;

    @Autowired
    public UserService(UserRepository userRepository, GlobalMouseListener globalMouseListener) {
        this.userRepository = userRepository;
        this.globalMouseListener = globalMouseListener;
    }

    public void startMotionCapturing() {
        globalMouseListener.startMouseMotionCapture();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
