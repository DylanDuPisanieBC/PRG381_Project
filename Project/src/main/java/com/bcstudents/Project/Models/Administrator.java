package com.bcstudents.Project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer admin_id;
    @Column(unique = true)
    private String admin_name;
    @Column
    private String admin_password;
    @Column
    private String admin_contact;
    @Column
    private String role;

    public Administrator(){
        super();
        this.role = "ADMIN";
    }

    public Administrator(Integer admin_id, String admin_name, String admin_password, String admin_contact,
            String role) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_contact = admin_contact;
        this.role = role;
    }

    public String getContact(){
        return this.admin_contact;
    }

    public void setContact(String contact){
        this.admin_contact = contact;
    }

    public void setAuthorities(String role) {
        this.role = role;
    }

    public String getAuthorities() {
        return this.role;
    }

    public String getPassword() {
        return this.admin_password;
    }

    public void setPassword(String password){
        this.admin_password = password;
    }

    public String getUsername() {
        return this.admin_name;
    }

    public void setUsername(String name){
        this.admin_name = name;
    }
    
}
