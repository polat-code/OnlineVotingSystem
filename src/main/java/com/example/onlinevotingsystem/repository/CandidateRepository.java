package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
