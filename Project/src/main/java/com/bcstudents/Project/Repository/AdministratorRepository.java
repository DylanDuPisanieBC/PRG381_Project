package com.bcstudents.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcstudents.Project.Models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
    @Query("SELECT a FROM Administrator a WHERE a.admin_name = :adminName")
    Administrator findByAdminName(@Param("adminName") String adminName);
}  
    

