package com.bcstudents.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcstudents.Project.Models.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.student_name = :usernameOrEmail OR s.student_email = :usernameOrEmail")
    Student findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
