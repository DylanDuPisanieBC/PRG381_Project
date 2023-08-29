package com.bcstudents.Project.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="register")
public class Register{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer register_id;
    @Column
    private String register_name;
    @Column
    private String register_address;
    @Column(unique = true)
    private String register_email;
    @Column
    private String register_password;
    @Column
    private String role;
    @Column
    private String course_name;

    public Register(){
        super();
        this.role = "USER";
    }

    public Register(Integer register_id, String register_name, String register_address, String register_email,
            String register_password, String role) {
        this.register_id = register_id;
        this.register_name = register_name;
        this.register_address = register_address;
        this.register_email = register_email;
        this.register_password = register_password;
        this.role = role;
    }

    public Integer getRegister_id() {
        return register_id;
    }

    public void setRegister_id(Integer register_id) {
        this.register_id = register_id;
    }

    public void setRegister_name(String register_name) {
        this.register_name = register_name;
    }

    public String getRegister_address() {
        return register_address;
    }

    public void setRegister_address(String register_address) {
        this.register_address = register_address;
    }

    public String getRegister_email() {
        return register_email;
    }

    public void setRegister_email(String register_email) {
        this.register_email = register_email;
    }

    public void setRegister_password(String register_password) {
        this.register_password = register_password;
    }

    public void setAuthorities(String role) {
        this.role = role;
    }

    public String getAuthorities() {
        return this.role;
    }

    public String getPassword() {
        return this.register_password;
    }


    public String getUsername() {
        return this.register_name;
    }
 
    
}
