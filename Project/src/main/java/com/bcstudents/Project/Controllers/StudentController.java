package com.bcstudents.Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String student() {
        return "student";
    }
    
}

