package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application,Long> {
}
