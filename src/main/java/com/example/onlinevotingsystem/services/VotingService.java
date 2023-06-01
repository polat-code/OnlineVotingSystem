package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.responses.GetAllApplicantsResponse;
import com.example.onlinevotingsystem.Dto.responses.VotingValidationResponse;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VotingService {

    private ElectionRepository electionRepository;
    private StudentRepository studentRepository;
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
        List<Student> students = election.getStudents();
        /*for(Student stud : students){
            GetAllApplicantsResponse applicantResponse = new GetAllApplicantsResponse()
        }
        */
        return  null;
    }
}
