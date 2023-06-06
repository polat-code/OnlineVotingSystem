package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.ApplicationDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDatesRepository  extends JpaRepository<ApplicationDates,Long> {

}
