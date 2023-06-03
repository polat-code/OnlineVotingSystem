package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.responses.GetAllApplicantsResponse;
import com.example.onlinevotingsystem.Dto.responses.VotingValidationResponse;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.User;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VotingService {

    private ElectionRepository electionRepository;
    private UserRepository userRepository;
    public VotingValidationResponse validateUserAlreadyVoteOrNot(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Long studentDepartmentId = user.getDepartment().getDepartmentId();
        Election election = electionRepository.findByDepartmentId(studentDepartmentId).orElseThrow();
        Long electionIdOfDepartment = election.getElectionId();
        List<User> users = election.getUsers();
        Boolean isAlreadyVoted = users.contains(user);

        VotingValidationResponse response = new VotingValidationResponse().builder().isAlreadyVoted(isAlreadyVoted).build();
        return  response;

    }

    public List<GetAllApplicantsResponse> getAllApplicants(Long userId) {
        List<GetAllApplicantsResponse> allApplicantsResponses = new ArrayList<>();

        User user = userRepository.findById(userId).get();
        Long departmentId = user.getDepartment().getDepartmentId();
        Election election = electionRepository.findByDepartmentId(departmentId).orElseThrow();
        List<Candidate> candidates = election.getCandidates();
        for(Candidate candidate : candidates){
            GetAllApplicantsResponse applicantResponse = new GetAllApplicantsResponse().builder()
                    .candidateId(candidate.getCandidateId())
                    .departmentName(candidate.getUser().getDepartment().getDepartmentName())
                    .grade(candidate.getUser().getGrade())
                    .name(candidate.getUser().getName())
                    .surname(candidate.getUser().getSurname())
                    .profilePhotoPath(candidate.getUser().getProfilePhotoPath())
                    .build();
            allApplicantsResponses.add(applicantResponse);
        }

        return  allApplicantsResponses;
    }
}
