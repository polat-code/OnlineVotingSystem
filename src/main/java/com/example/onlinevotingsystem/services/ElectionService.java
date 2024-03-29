package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.Dto.responses.GetResultResponse;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;

import com.example.onlinevotingsystem.models.*;
import com.example.onlinevotingsystem.repository.CandidateRepository;
import com.example.onlinevotingsystem.repository.ElectionDateRepository;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class ElectionService {

    private ElectionRepository electionRepository;

    private UserRepository userRepository;

    private CandidateRepository candidateRepository;

    private ModelMapperService modelMapperService;
    private ElectionDateRepository electionDateRepository;

    private DepartmentService departmentService;
    public ResponseEntity<Object> addElection(CreateElectionRequest createElectionRequest) {
        // Create Election for each department
        List<Department> departmentList = departmentService.getAllDepartments();
        if(departmentList == null) {
            return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        for(Department department: departmentList) {
            Election election = new Election().builder()
                    .electionStartDate(createElectionRequest.getElectionStartDate())
                    .electionName("Election Name")
                    .electionFinishDate(createElectionRequest.getElectionFinishDate())
                    .department(department)
                    .build();
            this.electionRepository.save(election);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        ElectionDate electionDate = new ElectionDate().builder()
                .electionStartDate(createElectionRequest.getElectionStartDate())
                .electionFinishDate(createElectionRequest.getElectionFinishDate())
                .createdAt(LocalDateTime.now().toString())
                .isActive(true)
                .build();
        this.electionDateRepository.save(electionDate);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    public void voteCandidateByElectionId(VoteRequest voteRequest) {

        Election election = electionRepository.findById(voteRequest.getElectionId()).get();
        boolean hasVoted = false;
        User voterUser = userRepository.findById(voteRequest.getUserId()).get();

        if (!userRepository.existsById(voterUser.getUserId())){
            for (User user : election.getUsers()){
                if (Objects.equals(user.getUserId(), voterUser.getUserId())) {
                    hasVoted = true;
                    break;
                }
            }
        }


        if (!hasVoted){
            election.getUsers().add(voterUser);
            voterUser.getElections().add(election);

            electionRepository.save(election);
            userRepository.save(voterUser);
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

    public List<GetResultResponse> getResults() {

        List<Election> elections = electionRepository.findAll();
        List<GetResultResponse> allResults = new ArrayList<>();

        for (Election election : elections){
            List<Candidate> candidates = election.getCandidates();
            Map<String,Integer> result = new LinkedHashMap<>();
            Collections.sort(candidates, (c1, c2) -> c2.getVoteCount() - c1.getVoteCount());
            for (Candidate candidate : candidates){
                String nameSurname = candidate.getUser().getName() + " " + candidate.getUser().getSurname();
                Integer voteCount = candidate.getVoteCount();
                result.put(nameSurname,voteCount);


            }

            GetResultResponse resultResponse= GetResultResponse.builder()
                    .result(result)
                    .electionId(election.getElectionId())
                    .deptName(election.getDepartment().getDepartmentName()).build();
            allResults.add(resultResponse);
        }
        return allResults;
    }
}
