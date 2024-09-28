package com.krilan.taskmanagement.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.entity.User;
import com.krilan.taskmanagement.repository.TaskRepository;
import com.krilan.taskmanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepo;

    public UserServiceImpl() {
        logger.info("UserServiceImpl created");
    }
    @Override
    public void addUser(User user) {
        logger.info("Encoding user password for: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        logger.info("User added: {}", user.getUsername());
    }
    @Override
    public void updateUser(User user) {
        userRepo.save(user);
        logger.info("User updated: {}", user.getUsername());
    }
    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
        logger.info("User deleted with ID: {}", id);
    }
    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public User findByUsername(String username) {
        return userRepo.findByUserName(username); 
    }
    @Override
    public boolean checkLogin(String username, String password) {
        User foundUser = userRepo.findByUserName(username);
        if (foundUser != null) {
            boolean isPasswordMatch = passwordEncoder.matches(password, foundUser.getPassword());
            if (isPasswordMatch) {
                logger.info("Login successful for user: {}", username);
                return true;
            } else {
                logger.info("Invalid password for user: {}", username);
            }
        } else {
            logger.info("User not found: {}", username);
        }
        return false;
    }
}