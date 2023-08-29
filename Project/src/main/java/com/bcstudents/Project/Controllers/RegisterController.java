package com.bcstudents.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Services.RegisterService;

@Controller
public class RegisterController {

    BCryptPasswordEncoder encoder;

    @Autowired
    RegisterService service;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("registerForm") Register form, Model mode) {

        encoder = new BCryptPasswordEncoder();

        Register userToRegister = new Register(0, form.getUsername(), form.getRegister_address(),
                form.getRegister_email(), encoder.encode(form.getPassword()), form.getCourse_name());

        if (userToRegister.isValid() == true) {
            Boolean added = service.AddRegistration(userToRegister);

            if (added) {
                mode.addAttribute("registerSuccess", added);
            } else {
                mode.addAttribute("registerFailed", !added);
            }
        } else {
            System.out.println("User Invalid");
        }

        return "register";
    }

}
