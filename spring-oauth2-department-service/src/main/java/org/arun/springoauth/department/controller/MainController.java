package org.arun.springoauth.department.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class MainController {
	@GetMapping(path = "/1")
    public String getDepartment() {
      return "Software Product Engineering";
    }
}
