package com.bcstudents.Project.Controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bcstudents.Project.Services.StudentService;

@Controller
public class HomeController {
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {


        if (authentication != null) {

            model.addAttribute("showDashboard", true);
        }
        else {
            model.addAttribute("showDashboard", false);
        }
        


        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}
