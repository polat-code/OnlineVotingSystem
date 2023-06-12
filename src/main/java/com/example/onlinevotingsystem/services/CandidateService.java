package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.responses.CandidateGetResponse;
import com.example.onlinevotingsystem.Dto.responses.GetAllCandidateResponse;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .build();
        candidateRepository.save(candidate);

        // Write a code to add Candidate Into candidate_for_election table.

        Election election = findElectionIdByUserId(student);
        if(election.getCandidates() == null){
            election.setCandidates(new ArrayList<>());
        }
        election.getCandidates().add(candidate);


        Candidate candidateFromDB = candidateRepository.findById(candidate.getCandidateId()).get();
        if(candidateFromDB.getElections() == null){
            candidateFromDB.setElections(new ArrayList<>());
        }
        candidateFromDB.getElections().add(election);
        electionRepository.save(election);
        candidateRepository.save(candidateFromDB);


    }

    private Election findElectionIdByUserId(Student student) {
        Department department = student.getDepartment();
        Election election = electionRepository.findByDepartmentId(department.getDepartmentId()).get();
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

    public List<GetAllCandidateResponse> getAllCandidates() {
        List<GetAllCandidateResponse> candidateResponseList = new ArrayList<>();
        List<Candidate> candidates = candidateRepository.findAll();

        for(Candidate candidate: candidates) {
            GetAllCandidateResponse candidateResponse = new GetAllCandidateResponse().builder()
                    .userId(candidate.getStudent().getUserId())
                    .name(candidate.getStudent().getName())
                    .surname(candidate.getStudent().getSurname())
                    .departmentName(candidate.getStudent().getDepartment().getDepartmentName())
                    .grade(candidate.getStudent().getGrade())
                    .build();
            candidateResponseList.add(candidateResponse);
        }
        return  candidateResponseList;
    }

    public ResponseEntity<Object> isCandidateOrNot(Long userId) {

        Candidate candidate = candidateRepository.findByUserId(userId);
        if(candidate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
