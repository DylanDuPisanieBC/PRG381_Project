package com.bcstudents.Project.Controllers;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RedirectController {

    // this route redirects users based on theire roles

    @GetMapping("/redirectByRole")
    public String redirectByRole(HttpServletRequest request, Authentication authentication) {
        
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // gets role for user
            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin"; // Redirect ADMIN to admin dashboard
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                return "redirect:/student"; // Redirect USER to student dashboard
            }
        }

        return "redirect:/";
    }

}
