package com.bcstudents.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Services.RegisterService;

@Controller
public class RegisterController {
    
    @Autowired
    RegisterService service;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ModelAttribute
    public String registerStudent(Register form, Model mode){
        
        Register userToRegister = new Register(1, "test", "somewhere st", "email@email.com", "password");
        Boolean added = false;

        added = service.AddRegistration(userToRegister);


        if(added){
            mode.addAttribute("registerSuccess", added);
        }else{
            mode.addAttribute("registerSuccess", added);
        }

        return "register";
    }

}
