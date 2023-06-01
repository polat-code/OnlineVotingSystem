package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ElectionService {

    private ElectionRepository electionRepository;

    private StudentRepository studentRepository;

    private CandidateRepository candidateRepository;

    private ModelMapperService modelMapperService;

    private DepartmentService departmentService;
    public void addElection(CreateElectionRequest createElectionRequest) {
        // Create Election for each department
        List<Department> departmentList = departmentService.getAllDepartments();
        for(Department department: departmentList) {
            Election election = new Election().builder()
                    .electionStartDate(createElectionRequest.getElectionStartDate())
                    .electionName(createElectionRequest.getElectionName())
                    .electionFinishDate(createElectionRequest.getElectionFinishDate())
                    .department(department)
                    .build();
            this.electionRepository.save(election);
        }


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
            voterStudent.getElections().add(election);

            electionRepository.save(election);
            studentRepository.save(voterStudent);
            Candidate candidate = candidateRepository.findById(voteRequest.getCandidateId()).orElseThrow();
            candidate.setVoteCount(candidate.getVoteCount()+1);
            candidateRepository.save(candidate);

        }



    }

    public void addCandidateToElectionById(Long candidateId, Long electionId) {
        Election election = electionRepository.findById(electionId).get();
        Candidate candidate = candidateRepository.findById(candidateId).get();
        election.getCandidates().add(candidate);
        candidate.getElections().add(election);
        candidateRepository.save(candidate);
        electionRepository.save(election);
    }
}
