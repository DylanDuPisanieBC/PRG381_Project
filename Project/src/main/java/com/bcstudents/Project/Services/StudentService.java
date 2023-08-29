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

import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Repository.StudentsRepository;

@Service
public class StudentService implements UserDetailsService {

   @Autowired
   StudentsRepository repo;

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

}
