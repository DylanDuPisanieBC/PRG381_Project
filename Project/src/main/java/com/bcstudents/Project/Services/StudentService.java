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

}
