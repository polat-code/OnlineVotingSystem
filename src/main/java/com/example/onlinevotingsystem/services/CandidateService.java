package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.responses.CandidateGetResponse;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.User;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateRepository candidateRepository;

    private UserRepository userRepository;

    private ElectionRepository electionRepository;




    public void createCandidate(CreateCandidateRequest createCandidateRequest) {
        User user = userRepository.findById(createCandidateRequest.getStudentId()).orElseThrow();
        Candidate candidate = new Candidate().builder()
                .user(user)
                .isCanceled(false)
                .elections(new ArrayList<>())
                .build();

        candidateRepository.save(candidate);
        // Write a code to add Candidate Into candidate_for_election table.
        
        Election election = findElectionIdByUserId(user);
        election.getCandidates().add(candidate);
        candidate.getElections().add(election);
        candidateRepository.save(candidate);
        electionRepository.save(election);

    }

    private Election findElectionIdByUserId(User user) {
        Department department = user.getDepartment();
        Election election = electionRepository.findByDepartmentId(user.getDepartment().getDepartmentId()).get();
        return election;
    }


    public CandidateGetResponse getCandidate(Long candidateId){
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        CandidateGetResponse candidateGetResponse = new CandidateGetResponse().builder()
                .studentName(candidate.getUser().getName())
                .studentSurname(candidate.getUser().getSurname())
                .studentNumber(candidate.getUser().getStudentNumber()).build();

        return candidateGetResponse;
    }
}
