package com.bcstudents.Project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Services.StudentService;

@Controller
public class StudentController {

    @Autowired
    StudentService repo;

    @GetMapping("/student/{id}")
    public String student(@PathVariable Integer id, Model model) {

        Student student = repo.getStudentById(id);
        List<Register> courses = repo.getAllStudentCourses(student.getStudent_email());
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        model.addAttribute("id", id);

        return "student";
    }

    @PostMapping("/student/{id}")
    public String studentRegCourse(@PathVariable Integer id, @RequestParam("course_name") String courseName) {

        Student student = repo.getStudentById(id);

        Boolean added = repo.registerForCourse(student.getStudent_email(), courseName);

        return "redirect:/student/" + id;
    }

    @GetMapping("/student")
    public String student() {

        return "student";
    }

}
