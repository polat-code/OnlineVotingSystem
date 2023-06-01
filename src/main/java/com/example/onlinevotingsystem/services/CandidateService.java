package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.responses.CandidateGetResponse;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateRepository candidateRepository;

    private StudentRepository studentRepository;

    private ElectionRepository electionRepository;




    public void createCandidate(CreateCandidateRequest createCandidateRequest) {
        Student student = studentRepository.findById(createCandidateRequest.getStudentId()).orElseThrow();
        Candidate candidate = new Candidate().builder()
                .student(student)
                .isCanceled(false)
                .elections(new ArrayList<>())
                .build();

        candidateRepository.save(candidate);
        // Write a code to add Candidate Into candidate_for_election table.
        
        Election election = findElectionIdByUserId(student);
        election.getCandidates().add(candidate);
        candidate.getElections().add(election);
        candidateRepository.save(candidate);
        electionRepository.save(election);

    }

    private Election findElectionIdByUserId(Student student) {
        Department department = student.getDepartment();
        Election election = electionRepository.findByDepartmentId(student.getDepartment().getDepartmentId()).get();
        return election;
    }


    public CandidateGetResponse getCandidate(Long candidateId){
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        CandidateGetResponse candidateGetResponse = new CandidateGetResponse().builder()
                .studentName(candidate.getStudent().getName())
                .studentSurname(candidate.getStudent().getSurname())
                .studentNumber(candidate.getStudent().getStudentNumber()).build();

        return candidateGetResponse;
    }
}
