package com.krilan.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.entity.User;

public interface TaskRepository extends JpaRepository <Task, Integer> {
		  User findByEmailid(String email);
//		  User findByUsername(String username);
}
