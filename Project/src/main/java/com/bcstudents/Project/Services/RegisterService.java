package com.bcstudents.Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Repository.RegisterRepository;

public class RegisterService implements UserDetailsService {

    @Autowired
    RegisterRepository repo;

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

        if (repo.findByEmail(registration.getRegister_email()).getRegister_email() == registration.getRegister_email()) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
