//package com.krilan.taskmanagement.service;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.krilan.taskmanagement.entity.User;
//import com.krilan.taskmanagement.repository.TaskRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private TaskRepository TaskRepository;
//
//	@Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = TaskRepository.findByEmail(email);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
//    }
//
//	
//}