package com.krilan.taskmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.entity.User;
import com.krilan.taskmanagement.service.impl.TaskService;
import com.krilan.taskmanagement.service.impl.UserService;

@Controller
public class ManagerController {
//	private List<User> taskData = new ArrayList<>();
//	private int id = 1;
//	
	@GetMapping("/")
	public String task() { 
		return "index";
	}
	
	private final UserService UserService;
    private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ManagerController(UserService userService) {
        this.UserService = userService;
    }
	 @GetMapping("/login")
	    public String LoginPageView(Model model) {
	        model.addAttribute("user", new User());
	        return "login"; 
	    }
	 
	 @PostMapping("/login")
	    public String login(@ModelAttribute User user, Model model) {
	        User foundUser = UserService.findByUsername(user.getUsername());

	        if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
	            logger.info("User {} successfully logged in", user.getUsername());
	            return "redirect:/index";
	        }
	        model.addAttribute("error", "Invalid username or password");
	        logger.warn("Invalid login attempt for user: {}", user.getUsername());
	        return "/login"; 
	    }
	 
	   // Show task registration page
//	    @GetMapping("/tasks")
//	    public String showTaskPage(Model model) {
//	        model.addAttribute("task", new Task());  
//	        model.addAttribute("taskData", taskData);  
//	        return "task"; 
//	    }
//
//	    // Handle task creation
//	    @PostMapping("/tasks")
//	    public String createTask(@ModelAttribute Task task, BindingResult result, Model model) {
//	        if (result.hasErrors()) {
//	            return "task-registration";
//	        }
//	        task.setId(id++);  
//	        taskData.add(task);  
//	        return "redirect:/tasks";  
//	    }
}
