package com.bcstudents.Project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Services.RegisterService;
import com.bcstudents.Project.Services.StudentService;

import org.springframework.ui.Model;

@Controller
public class AdministratorController {

    @Autowired
    RegisterService serviceRegistration;
    
    @Autowired
    StudentService serviceStudents;

    @GetMapping("/admin")
    public String admin(Model model) {

        List<Register> registration = serviceRegistration.getAllRegistrations();

        model.addAttribute("registration", registration);

        // create students with registrations for admin use

        return "admin";

    }

    @GetMapping("/admin/registrations")
    public String adminRegistrations(Model model) {

        List<Register> registration = serviceRegistration.getAllRegistrationsPending();
        
        model.addAttribute("registration", registration);

        // create students with registrations for admin use

        return "registrations";

    }

    @GetMapping("/admin/students")
    public String adminStudents(Model model) {

        List<Student> students = serviceStudents.getAllStudents();

        model.addAttribute("students", students);

        return "students";

    }

    @GetMapping("/admin/students/remove/{id}")
    public String adminStudents(@PathVariable Integer id) {

        serviceStudents.removeStudent(id);

        return "redirect:/admin/students";

    }

    @GetMapping("/admin/registrations/accept/{id}")
    public String adminAcceptReg(@PathVariable Integer id) {
        serviceRegistration.acceptRegistration(id);
        return "redirect:/admin/registrations";
    }

    @GetMapping("/admin/registrations/decline/{id}")
    public String adminDeclineReg(@PathVariable Integer id) {

        serviceRegistration.declineRegistration(id);

        return "redirect:/admin/registrations";
    }

}
