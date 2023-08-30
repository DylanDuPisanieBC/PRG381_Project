package com.bcstudents.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Services.StudentService;

@Controller
public class RegisterController {

    BCryptPasswordEncoder encoder;

    @Autowired
    StudentService service;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("registerForm") Student form, Model mode) {

        encoder = new BCryptPasswordEncoder();

        Student studentToRegister = new Student(0, form.getUsername(), form.getStudent_address(),
                form.getStudent_email(), encoder.encode(form.getPassword()));

        if (studentToRegister.isValid() == true) {
            Boolean added = service.AddStudent(studentToRegister);

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
