package com.bcstudents.Project.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Repository.RegisterRepository;
import com.bcstudents.Project.Repository.StudentsRepository;


@Service
public class StudentService implements UserDetailsService {

   @Autowired
   StudentsRepository repo;
   @Autowired
   RegisterRepository repoRegister;

   public Student getStudentById(int studentId) {
      return repo.findById(studentId).get();
   }

   public List<Student> getAllStudents() {
      List<Student> students = new ArrayList<Student>();
      repo.findAll().forEach(student -> students.add(student));
      return students;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Student student = repo.findByUsernameOrEmail(username);
      if (student == null) {
         throw new UsernameNotFoundException("Username not found: " + username);
      }

      // Create a collection of a single granted authority (role)
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(student.getAuthorities()));

      // Create a UserDetails object with the student's information and authorities
      UserDetails userDetails = new User(student.getUsername(), student.getPassword(), authorities);

      return userDetails;

   }

   public Integer getStudentIdByUsername(String username) {
      Student student = repo.findByUsernameOrEmail(username);
      return student != null ? student.getStudent_id() : null;
   }

   public boolean AddStudent(Student student) {

      if (findStudent(student) == false) {
         try {
            repo.save(student);
            return true;
         } catch (Exception e) {
            return false;
         }
      }
      return false;
   }

   public void removeStudent(Integer id) {

      Student student = repo.findById(id).get();

      if(student != null) { repo.delete(student);}

   }

   public Boolean findStudent(Student student) {

      if (repo.findByUsernameOrEmail(student.getStudent_email()) != null) {

         return true;
      }
      return false;
   }

   public Student getStudent(String email) {

      Student student = repo.findByUsernameOrEmail(email);
      
      return student;
   }

   public List<Register> getAllStudentCourses(String email) {

      List<Register> courses = new ArrayList<Register>();
      courses = repoRegister.findAllByEmail(email);

      return courses;

   }

   public boolean registerForCourse(String email, String course) {

      Student student = repo.findByUsernameOrEmail(email);
      List<Register> registerList = repoRegister.findAllByEmail(email);

      for (Register reg : registerList) {
         if (reg.getCourse_name().equals(course)) {
            return false;
         }
      }

      Register registerNew = new Register(0, student.getStudent_name(), student.getStudent_address(), email,
            student.getPassword(), course);
      if (registerNew.isValid()) {
         repoRegister.save(registerNew);
         return true;
      } else {
         return false;
      }

   }

   BCryptPasswordEncoder encoder;  

   public void editStudent(Student student) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      
      //input student from form -> can be inserted or updated in database

      List<Student> students = repo.findAll();
      boolean emailUnique = true;
      
      for (Student s : students) {
          if (s != student) {
              if (s.getStudent_email().equals(student.getStudent_email())) {
                  emailUnique = false;
              }
          }
      }
      
      Student editedStudent = new Student();
      
      System.out.println("START EMAIL: " + student.getStudent_email());
      
      System.out.println("ID: " + editedStudent.getStudent_id());
      if (emailUnique) {
          editedStudent.setStudent_email(student.getStudent_email());
      }else{
         editedStudent.setStudent_email()
      }

      repo.find

      System.out.println("EMAIL: " + editedStudent.getStudent_email());
      if (!student.getPassword().isEmpty()) {
          editedStudent.setStudent_password(repo.findByUsernameOrEmail(student.getStudent_email()).getPassword());
      } else {
          editedStudent.setStudent_password(encoder.encode(student.getPassword()));
      }
      System.out.println("PASS: " + editedStudent.getPassword());
      editedStudent.setStudent_address(student.getStudent_address());
      editedStudent.setStudent_name(student.getStudent_name());
  
      if (editedStudent.isValid()) {
          System.out.println("EDITED USER IS VALID");
      } else {
          System.out.println("EDITED USER IS INVALID");
      }
  }
  

}

//fix student edit on admin
//make sure email and name reg is unique
