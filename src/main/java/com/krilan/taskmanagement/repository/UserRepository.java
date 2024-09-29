package com.krilan.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krilan.taskmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	   User findByEmail(String email);
}
