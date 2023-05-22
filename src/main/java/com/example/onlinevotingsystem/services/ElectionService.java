package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ElectionService {

    private ElectionRepository electionRepository;

    private StudentRepository studentRepository;

    private CandidateRepository candidateRepository;

    private ModelMapperService modelMapperService;



    public void addElection(CreateElectionRequest createElectionRequest) {

        Election election = this.modelMapperService.forResponse().map(createElectionRequest,Election.class);
        this.electionRepository.save(election);

    }

    public void voteCandidateByElectionId(VoteRequest voteRequest) {

        Election election = electionRepository.findById(voteRequest.getElectionId()).get();
        boolean hasVoted = false;
        Student voterStudent= studentRepository.findById(voteRequest.getUserId()).get();

        if (!studentRepository.existsById(voterStudent.getUserId())){
            for (Student student : election.getStudents()){
                if (Objects.equals(student.getUserId(), voterStudent.getUserId())) {
                    hasVoted = true;
                    break;
                }
            }
        }

        if (!hasVoted){
            election.getStudents().add(voterStudent);
            Candidate candidate = candidateRepository.findById(voteRequest.getCandidateId()).orElseThrow();
            candidate.setVoteCount(candidate.getVoteCount()+1);
            candidateRepository.save(candidate);
        }

    }
}
