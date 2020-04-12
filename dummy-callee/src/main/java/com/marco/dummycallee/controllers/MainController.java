package com.marco.dummycallee.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/callee")
public class MainController {
	
	@GetMapping()
	public String getDepartment() {
		return "Hello from the callee";
	}
}
