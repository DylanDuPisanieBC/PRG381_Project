package com.bcstudents.Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    
    @GetMapping("/error")
    public String showError(){
        return "error";
    }

}
