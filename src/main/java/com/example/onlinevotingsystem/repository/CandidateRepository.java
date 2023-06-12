package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query(value = "select * from candidate where f_user_id = ?1",nativeQuery = true)
    Candidate findByUserId(Long userId);
}
