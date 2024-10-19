package com.krilan.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.krilan.taskmanagement.entity.Task;
import com.krilan.taskmanagement.entity.User;
import com.krilan.taskmanagement.service.impl.UserService;

@Controller
public class AuthController {
	
	@Autowired
	UserService userService;

    

    @GetMapping("/registration")
    public String showRegis(Model model) {
        model.addAttribute("user", new User());  // Add empty user object to model
        return "registration";  // Return the view name (registration.html)
    }

    @PostMapping("/registration")
    public String processRegis( User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";  // If validation fails, show the form again
        }
       
    	if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "register"; 
        }
       
        userService.addUser(user); 
        return "redirect:/login"; 
        
    }
    
    @GetMapping("/login")
    public String LoginPageView(Model model) {
        model.addAttribute("user", new User());
        return "login"; 
    }
}