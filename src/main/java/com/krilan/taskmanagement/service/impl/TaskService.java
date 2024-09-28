package com.krilan.taskmanagement.service.impl;

import com.krilan.taskmanagement.entity.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {
	Task saveTask();
    // Create a new task
    Task createTask(Task task);

    // Get all tasks
    List<Task> getAllTasks();

    // Get a task by ID
    Optional<Task> getTaskById(int id);

    // Update an existing task
    Task updateTask(int id, Task taskDetails);

    // Delete a task by ID
    void deleteTask(int id);

    // Get tasks by user email
    List<Task> getTasksByEmail(String email);
}
