package com.krilan.taskmanagement.service.impl;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(java.time.LocalDateTime.now());
        task.setUpdatedAt(java.time.LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task updateTask(int id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setPriority(taskDetails.getPriority());
            existingTask.setDeadline(taskDetails.getDeadline());
            existingTask.setCompleted(taskDetails.isCompleted());
            existingTask.setUpdatedAt(java.time.LocalDateTime.now());
            return taskRepository.save(existingTask);
        } else {
            return null;  // Handle case where task not found
        }
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByEmail(String email) {
        return taskRepository.findByEmailid(email);
    }
}
