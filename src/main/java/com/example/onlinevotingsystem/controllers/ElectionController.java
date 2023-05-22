package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.services.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elections")
@AllArgsConstructor
public class ElectionController {

    private ElectionService electionService;

    @PostMapping("")
    public void addElection(@RequestBody CreateElectionRequest createElectionRequest){
        this.electionService.addElection(createElectionRequest);
    }

    @PutMapping("")
    public void voteCandidateByElectionId(@RequestBody VoteRequest voteRequest){
        this.electionService.voteCandidateByElectionId(voteRequest);
    }

}
