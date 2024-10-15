package com.krilan.taskmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.repository.TaskRepository;
import com.krilan.taskmanagement.repository.UserRepository;
import com.krilan.taskmanagement.service.impl.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskDetailController {

	@Autowired
	private TaskService taskService;

	// Display the task registration form and list of tasks
	@GetMapping({"","/"})
	public String index(Model model) {

		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "tasks/index";
	}
	
	
	@GetMapping("/create")
	public String showCreteTaskForm(Model model) {
		model.addAttribute("task",new Task());
		return "tasks/create-form";
	}
	
	@PostMapping("/store")
	public String creteNewTask(@ModelAttribute Task task) {
		taskService.createTask(task);
		return "redirect:/tasks";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateTaskForm(@PathVariable Long id,Model model) {
		Optional<Task> task = taskService.getById(id);
		if(task.isPresent()) {
			model.addAttribute("task",task.get());
			return "tasks/update-form";
		}
		return "redirect:/tasks";	
	}
	
	@PostMapping("/edit/{id}")
	public String updateTask(@PathVariable Long id,@ModelAttribute Task task) {
		Task updatedTask = taskService.updateTask(id, task);
		if(updatedTask!=null) {
			return "redirect:/tasks";

		}
		return "tasks/update-form";
	}

	// Handle task completion
    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        Optional<Task> optionalTask = taskService.getById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            taskService.saveTask(task);
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getById(id); 
        if (task.isPresent()) {
            taskService.deleteTask(task.get().getId());  
            model.addAttribute("message", "Task deleted successfully");
        } else {
            model.addAttribute("error", "Task not found");
        }
        return "redirect:/tasks";  
    }
}

    