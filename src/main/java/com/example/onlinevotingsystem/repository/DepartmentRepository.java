package com.example.onlinevotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinevotingsystem.models.Department;
import org.springframework.data.jpa.repository.Query;

// hata var :
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query(value = "select * from department where department_name = ?1",nativeQuery = true)
    Department findByDepartmentName(String departmentName);
}
