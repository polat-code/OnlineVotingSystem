package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.ElectionDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionDateRepository extends JpaRepository<ElectionDate,Long> {


}
