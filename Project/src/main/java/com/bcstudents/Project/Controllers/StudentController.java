package com.bcstudents.Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Services.StudentService;

@Controller
public class StudentController {

    @Autowired
    StudentService repo;

    @GetMapping("/student/{id}")
    public String student(@PathVariable Integer id, Model model) {

        Student student = repo.getStudentById(id);
        model.addAttribute("student", student);

        return "student";
    }

    @GetMapping("/student")
    public String student() {

        return "student";
    }

}
