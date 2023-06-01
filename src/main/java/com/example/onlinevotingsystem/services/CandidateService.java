package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.responses.CandidateGetResponse;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateRepository candidateRepository;

    private StudentRepository studentRepository;




    public void createCandidate(CreateCandidateRequest createCandidateRequest) {
        Student student = studentRepository.findById(createCandidateRequest.getStudentId()).orElseThrow();
        Candidate candidate = new Candidate().builder()
                .student(student)
                .isCanceled(false)
                .build();

        candidateRepository.save(candidate);
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
