package com.krilan.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {
		
	@GetMapping("/manager")
	public String index() {
		return "manager/index";
	}
}
