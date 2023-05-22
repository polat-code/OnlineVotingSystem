package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Faculty findByFacultyName(String facultyName);

    boolean existsByFacultyName(String facultyName);
}
