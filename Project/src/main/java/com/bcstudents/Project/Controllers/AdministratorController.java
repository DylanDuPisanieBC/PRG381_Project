package com.bcstudents.Project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/students/getstudentdata")
    public ResponseEntity<Student> adminStudents(@RequestParam String email) {

        Student student = serviceStudents.getStudent(email);       
        if(student != null) { 
            return ResponseEntity.ok(student);
            }else{
               return ResponseEntity.notFound().build();
            }
    }

    @PostMapping("/admin/students/edit")
    public String adminStudentEdit(@ModelAttribute("studentFormEdit") Student form) {

        Student student = form;       

        serviceStudents.editStudent(student);

       return "redirect:/admin/students";

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

    BCryptPasswordEncoder encoder;

    @PostMapping("/admin/students/add")
    public String addStudent(@ModelAttribute("studentFormAdd") Student form, Model mode) {

        encoder = new BCryptPasswordEncoder();

        Student studentToRegister = new Student(0, form.getUsername(), form.getStudent_address(),
                form.getStudent_email(), encoder.encode(form.getPassword()));

        if (studentToRegister.isValid() == true) {
            Boolean added = serviceStudents.AddStudent(studentToRegister);

            if (added) {
                mode.addAttribute("addSuccess", added);
            } else {
                mode.addAttribute("addFailed", !added);
            }
        } else {
            System.out.println("User Invalid");
        }
        return "redirect:/admin/students";
    }

}
