package com.bcstudents.Project.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministratorController {

        @GetMapping("/admin")
        public String admin(Authentication authentication) {
        return "admin";

    }
}
