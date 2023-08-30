package com.bcstudents.Project.Controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bcstudents.Project.Services.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RedirectController {

    // this route redirects users based on theire roles

    @Autowired
    StudentService studentService;

    @GetMapping("/redirectByRole")
    public String redirectByRole(HttpServletRequest request, Authentication authentication) {

        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin";
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                Integer userId = studentService.getStudentIdByUsername(username); // Use your service here
                return "redirect:/student/" + userId;
            }
        }

        return "redirect:/";
    }
}
