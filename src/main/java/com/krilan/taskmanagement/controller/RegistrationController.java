package com.krilan.taskmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.krilan.taskmanagement.entity.User;

@Controller
public class RegistrationController {

    private List<String> taskData = new ArrayList<>(); // Assuming 'Data' is the correct class name for 'user'
    private int id = 1; // Initialize id

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
       
        user.setId(id);
        id++;
        taskData.add("user");  // Save user data

        model.addAttribute("message", "Registration successful!");
        model.addAttribute("user", user);  // Add the user to the model for confirmation
        
        return "index";  // Return the index view after registration
    }

}