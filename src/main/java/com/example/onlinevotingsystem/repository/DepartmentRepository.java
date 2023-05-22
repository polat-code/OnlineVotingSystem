package com.example.onlinevotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinevotingsystem.models.Department;

// hata var :
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
