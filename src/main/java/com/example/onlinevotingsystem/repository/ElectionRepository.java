package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionRepository extends JpaRepository<Election, Long> {



}
