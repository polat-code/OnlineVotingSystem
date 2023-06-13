package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.responses.GetAllApplicantsResponse;
import com.example.onlinevotingsystem.Dto.responses.VotingValidationResponse;
import com.example.onlinevotingsystem.models.*;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VotingService {

    private ElectionRepository electionRepository;
    private StudentRepository studentRepository;
    private CandidateRepository candidateRepository;
    public VotingValidationResponse validateUserAlreadyVoteOrNot(Long userId) {
        Student student = studentRepository.findById(userId).orElseThrow();
        Long studentDepartmentId = student.getDepartment().getDepartmentId();
        Election election = electionRepository.findByDepartmentId(studentDepartmentId).orElseThrow();
        Long electionIdOfDepartment = election.getElectionId();
        List<Student> students = election.getStudents();
        Boolean isAlreadyVoted = students.contains(student);

        VotingValidationResponse response = new VotingValidationResponse().builder().isAlreadyVoted(isAlreadyVoted).build();
        return  response;

    }

    public List<GetAllApplicantsResponse> getAllApplicants(Long userId) {
        List<GetAllApplicantsResponse> allApplicantsResponses = new ArrayList<>();

        Student student = studentRepository.findById(userId).get();
        Long departmentId = student.getDepartment().getDepartmentId();
        Election election = electionRepository.findByDepartmentId(departmentId).orElseThrow();
        List<Candidate> candidates = election.getCandidates();
        for(Candidate candidate : candidates){
            GetAllApplicantsResponse applicantResponse = new GetAllApplicantsResponse().builder()
                    .candidateId(candidate.getCandidateId())
                    .departmentName(candidate.getStudent().getDepartment().getDepartmentName())
                    .grade(candidate.getStudent().getGrade())
                    .name(candidate.getStudent().getName())
                    .surname(candidate.getStudent().getSurname())
                    .profilePhotoPath(candidate.getStudent().getProfilePhotoPath())
                    .build();
            allApplicantsResponses.add(applicantResponse);
        }

        return  allApplicantsResponses;
    }

    public ResponseEntity<Object> votingCandidate(Long userId, Long candidateId) {
        Optional<Student> student = this.studentRepository.findById(userId);
        if(student.isEmpty()) {
            new ResponseEntity<>("There is no such a userId : " + userId,HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<Candidate> candidate = this.candidateRepository.findById(candidateId);
        if(candidate.isEmpty()){
            new ResponseEntity<>("There is no such a candidateId : " + candidateId,HttpStatus.NOT_ACCEPTABLE);
        }
        student.get().setIsVoted(true);
        List<Election> elections = student.get().getElections();


        if(elections.isEmpty()){
            // Increase vote count and save it into database
            candidate.get().setVoteCount(candidate.get().getVoteCount() + 1);

            // Save that whether user votes
            Long departmentId = candidate.get().getStudent().getDepartment().getDepartmentId();
            Election election = electionRepository.findByDepartmentId(departmentId).orElseThrow();

            List<Student> students = election.getStudents();
            students.add(student.get());

            elections.add(election);


            this.candidateRepository.save(candidate.get());
            this.electionRepository.save(election);
            this.studentRepository.save(student.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>("This userid :" + userId + " has voted already!",HttpStatus.NOT_ACCEPTABLE);
        }



    }
}
