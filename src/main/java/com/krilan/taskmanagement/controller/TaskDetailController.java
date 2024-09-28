package com.krilan.taskmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.service.impl.TaskService;

@Controller
public class TaskDetailController {

    @Autowired
    private TaskService taskService;

    // Display the task registration form and list of tasks
    @GetMapping("/tasks")
    public String showTaskForm(Model model) {
       // model.addAttribute("task", new Task());  // Empty Task object for the form
       // List<Task> tasks = taskService.getAllTasks();
       // model.addAttribute("taskData", tasks);  // Add all tasks to the model
        return "taskdetail";  // Corrected view name
    }

    // Handle the task registration form submission
    @PostMapping("/tasks")
    public String registerTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks"; 
    }

    // Handle task completion
    @GetMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
       // Task task = taskService.getTaskById(id);
       // task.setCompleted(true);
        //taskService.saveTask(task);
        return "redirect:/tasks";
    }

    // Handle task deletion
    @GetMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
       // taskService.deleteTask(id);
        return "redirect:/tasks";
    }

}
