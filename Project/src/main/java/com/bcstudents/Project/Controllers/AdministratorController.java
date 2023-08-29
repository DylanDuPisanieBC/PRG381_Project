package com.bcstudents.Project.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Services.RegisterService;

@Controller
public class AdministratorController {

        @Autowired
        RegisterService service;

        @GetMapping("/admin")
        public String admin(Authentication authentication) {

            List<Register> registration = new ArrayList<Register>();

        return "admin";

    }
}
