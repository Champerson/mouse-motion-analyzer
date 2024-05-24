package com.havrylchenko.mousemotionanalyzer.service;

import com.havrylchenko.mousemotionanalyzer.listener.GlobalMouseListener;
import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import com.havrylchenko.mousemotionanalyzer.model.User;
import com.havrylchenko.mousemotionanalyzer.repository.MotionRepository;
import com.havrylchenko.mousemotionanalyzer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private MotionRepository motionRepository;
    private UserRepository userRepository;
    private GlobalMouseListener globalMouseListener;

    @Autowired
    public UserService(MotionRepository motionRepository, UserRepository userRepository, GlobalMouseListener globalMouseListener) {
        this.motionRepository = motionRepository;
        this.userRepository = userRepository;
        this.globalMouseListener = globalMouseListener;
    }

    public void startMotionCapturing(String username, int time) {
        User user = this.getUserByUsername(username);
        MotionStorage motions = globalMouseListener.startMouseMotionCapture(time);

        motions.setUser(user);

        motionRepository.save(motions);
    }

    public List<MotionStorage> findAllMotionsStoragesByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return motionRepository.findByUserId(user.getId());
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
