package com.example.onlinevotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinevotingsystem.models.Department;

import java.util.Optional;

// hata var :
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByDepartmentName(String name);
}
