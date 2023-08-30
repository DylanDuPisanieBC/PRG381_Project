package com.bcstudents.Project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcstudents.Project.Models.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    @Query("SELECT r FROM Register r WHERE r.student_email = :registerEmail")
    Register findByEmail(@Param("registerEmail") String Email);

    @Query("SELECT r FROM Register r WHERE r.student_email = :registerEmail")
    List<Register> findAllByEmail(@Param("registerEmail") String email);
}
