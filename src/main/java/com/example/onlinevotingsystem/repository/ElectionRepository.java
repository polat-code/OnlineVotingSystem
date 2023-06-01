package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ElectionRepository extends JpaRepository<Election, Long> {

    @Query(value = "select * from Election  where  f_department_id = ?1",nativeQuery = true)
    Optional<Election> findByDepartmentId(Long studentDepartmentId);
}
