package com.bcstudents.Project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "register")
public class Register {

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

    public Register() {
        super();
        this.role = "USER";
    }

    public Register(Integer register_id, String register_name, String register_address, String register_email,
            String register_password, String course_name) {

        this.register_id = register_id;
        this.register_name = register_name;
        this.register_address = register_address;
        this.register_email = register_email;
        this.register_password = register_password;
        this.course_name = course_name;
        this.role = "ROLE_USER";
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

    public String getRegister_name() {
        return this.register_name;
    }

    public String getRegister_password() {
        return this.register_password;
    }

    public String getRole() {
        return this.role;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public String Display() {

        return "\nid: " + this.register_id + "\nname: " + this.register_name + "\nemail: " + this.register_email
                + "\naddress: " + this.register_address + "\npassword: " + this.register_password + "\ncourse: "
                + this.course_name + "\nrole: " + this.role;

    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Boolean isValid() {

        if (this.course_name.length() > 0 && this.register_address.length() > 0 && this.register_password.length() > 0
                && this.register_email.length() > 0 && this.register_name.length() > 0) {
            return true;
        }

        return false;

    }

}
