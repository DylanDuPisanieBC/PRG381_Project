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

import com.bcstudents.Project.Models.Administrator;
import com.bcstudents.Project.Repository.AdministratorRepository;

@Service
public class AdministratorService implements UserDetailsService {
    
    @Autowired
    AdministratorRepository repo;

    public Administrator getAdminById(int admin_id) {
      return repo.findById(admin_id).get();
   } 

   public List<Administrator> getAllStudents() {
      List<Administrator> admins = new ArrayList<Administrator>();
      repo.findAll().forEach(admin -> admins.add(admin));
      return  admins;
   }

   // method implemented by UserDetailService class

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Administrator admin = repo.findByAdminName(username);
      if (admin == null) {
        throw new UsernameNotFoundException("Username not found: " + username);
      }

      // Create a collection of a single granted authority (role)
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(admin.getAuthorities()));

      // Create a UserDetails object with the student's information and authorities
      UserDetails userDetails = new User(admin.getUsername(), admin.getPassword(), authorities);

      return userDetails;
   } 

}