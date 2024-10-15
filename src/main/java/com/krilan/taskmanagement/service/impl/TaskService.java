package com.krilan.taskmanagement.service.impl;

import com.krilan.taskmanagement.entity.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
	
	Optional<Task> getById(Long id);
    Task createTask(Task task);

    // Get all tasks
    List<Task> getAllTasks();

    // Get a task by ID
    Optional<Task> getTaskById(Long id);

    // Update an existing task
    Task updateTask(Long id, Task taskDetails);

    // Delete a task by ID
    void deleteTask(Long id);

    // Get tasks by user email
    Task getTasksByEmail(String email);
    
    //save tasks
    Task saveTask(Task task);
}

