package com.bcstudents.Project.Models;

public class StudentEdit {


    private Integer student_id;
    private String student_name;
    private String student_address;
    private String student_email_original;
    private String student_email;
    private String student_password;
    private String role;

    public StudentEdit() {
        super();
        this.role = "ROLE_USER";
    }

    public StudentEdit(Integer student_id, String student_name, String student_address, String student_email,
            String student_password) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_address = student_address;
        this.student_email = student_email;
        this.student_email_original = this.student_email;
        this.student_password = student_password;
        this.role = "ROLE_USER";
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_address() {
        return student_address;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
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

    public String getStudent_name() {
        return student_name;
    }

    public String Display() {

        return "\nid: " + this.student_id + "\nname: " + this.student_name + "\nemail: " + this.student_email
                + "\naddress: " + this.student_address + "\npassword: " + this.student_password + "\nrole: "
                + this.role;

    }

    public Boolean isValid() {

        if (this.student_name.length() > 0 && this.student_address.length() > 0 && this.student_email.length() > 0
                && this.student_password.length() > 0) {
            return true;
        }

        return false;

    }

    public String getStudent_email_original() {
        return student_email_original;
    }

    public void setStudent_email_original(String student_email_original) {
        this.student_email_original = student_email_original;
    }

    public String getStudent_password() {
        return student_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
