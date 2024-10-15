package com.krilan.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krilan.taskmanagement.entity.Task;

public interface TaskRepository extends JpaRepository <Task, Long> {
		  Task findByEmail(String email);
		  Task deleteById(int id);
}
