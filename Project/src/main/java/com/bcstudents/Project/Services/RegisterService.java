package com.bcstudents.Project.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bcstudents.Project.Models.Register;
import com.bcstudents.Project.Models.Student;
import com.bcstudents.Project.Repository.RegisterRepository;
import com.bcstudents.Project.Repository.StudentsRepository;

@Service
public class RegisterService implements UserDetailsService {

    @Autowired
    RegisterRepository repo;
    @Autowired
    StudentsRepository studentRepo;

    public List<Register> getAllRegistrations() {
        List<Register> registrations = new ArrayList<Register>();
        repo.findAll().forEach(registration -> registrations.add(registration));
        return registrations;
    }

    public void declineRegistration(Integer id) {
        repo.delete(repo.findById(id).get());
        ;

    }

    public void acceptRegistration(Integer id) {
        Register reg = repo.findById(id).get();
        Student student = new Student(0, reg.getRegister_name(), reg.getRegister_address(), reg.getRegister_email(),
                reg.getPassword());
        try {
            studentRepo.save(student);
            repo.delete(reg);
        } catch (Exception e) {
            System.out.println("Failed to Accept Student:" + e.getMessage() + student.Display());
        }

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
