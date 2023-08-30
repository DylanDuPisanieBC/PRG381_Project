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
    private String student_name;
    @Column
    private String student_address;
    @Column
    private String student_email;
    @Column
    private String student_password;
    @Column
    private String role;
    @Column
    private String course_name;
    @Column
    private String status;

    public Register() {
        super();
        this.role = "ROLE_USER";
    }

    public Register(Integer register_id, String register_name, String register_address, String register_email,
            String register_password, String course_name) {

        this.register_id = register_id;
        this.student_name = register_name;
        this.student_address = register_address;
        this.student_email = register_email;
        this.student_password = register_password;
        this.course_name = course_name;
        this.role = "ROLE_USER";
        this.status = "Pending";
    }

    public String getStatus(){
        return this.status;
    }

    public Boolean getStatusBool(){
        
        if(this.status == "Pending"){
            return false;
        }else{
            return true;
        }

    }

    public void setStatus(boolean state){
        if(state){
            this.status = "Active";
        }else{
            this.status = "Pending";
        }
    }

    public Integer getRegister_id() {
        return register_id;
    }

    public void setRegister_id(Integer register_id) {
        this.register_id = register_id;
    }

    public void setRegister_name(String register_name) {
        this.student_name = register_name;
    }

    public String getRegister_address() {
        return student_address;
    }

    public void setRegister_address(String register_address) {
        this.student_address = register_address;
    }

    public String getRegister_email() {
        return student_email;
    }

    public void setRegister_email(String register_email) {
        this.student_email = register_email;
    }

    public void setRegister_password(String register_password) {
        this.student_password = register_password;
    }

    public void setAuthorities(String role) {
        this.role = role;
    }

    public String getAuthorities() {
        return this.role;
    }

    public String getPassword() {
        return this.student_password;
    }

    public String getUsername() {
        return this.student_name;
    }

    public String getRegister_name() {
        return this.student_name;
    }

    public String getRegister_password() {
        return this.student_password;
    }

    public String getRole() {
        return this.role;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public String Display() {

        return "\nid: " + this.register_id + "\nname: " + this.student_name + "\nemail: " + this.student_email
                + "\naddress: " + this.student_address + "\npassword: " + this.student_password + "\ncourse: "
                + this.course_name + "\nrole: " + this.role;

    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Boolean isValid() {

        if (this.course_name.length() > 0 && this.student_address.length() > 0 && this.student_password.length() > 0
                && this.student_email.length() > 0 && this.student_name.length() > 0) {
            return true;
        }

        return false;

    }

}
