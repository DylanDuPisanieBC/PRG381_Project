package com.bcstudents.Project.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcstudents.Project.Models.Administrator;
import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Repository.RegisterRepository;

@Service
public class RegisterService implements UserDetailsService {

    @Autowired
    RegisterRepository repo;

    public List<Register> getAllRegistrations() {
      List<Register> registrations = new ArrayList<Register>();
      repo.findAll().forEach(registration -> registrations.add(registration));
      return  registrations;
   }

    public boolean AddRegistration(Register registration) {

        if (findRegistration(registration) == false) {
            try {
                repo.save(registration);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }


    public Boolean findRegistration(Register registration) {

        
        if (repo.findByEmail(registration.getRegister_email()) != null) {

            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
