package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.services.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elections")
@AllArgsConstructor
public class ElectionController {

    private ElectionService electionService;

    // Add a triger to start election
    @PostMapping("")
    public void addElection(@RequestBody CreateElectionRequest createElectionRequest){
        this.electionService.addElection(createElectionRequest);
    }

    @PutMapping("")
    public void voteCandidateByElectionId(@RequestBody VoteRequest voteRequest){
        this.electionService.voteCandidateByElectionId(voteRequest);
    }

    @PutMapping("/{electionId}/{candidateId}")
    public void addCandidateToElectionById(@PathVariable Long candidateId,@PathVariable Long electionId){
        this.electionService.addCandidateToElectionById(candidateId,electionId);
    }

    // Handle Election Result


}
