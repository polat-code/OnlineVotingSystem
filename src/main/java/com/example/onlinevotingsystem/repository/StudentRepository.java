package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByEmail(String email);
}
