package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.requests.VoteRequest;
import com.example.onlinevotingsystem.Dto.responses.GetResultResponse;
import com.example.onlinevotingsystem.services.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elections")
@AllArgsConstructor
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
//@CrossOrigin(origins =  "http://localhost:3000")
public class ElectionController {

    private ElectionService electionService;

    // Add a triger to start election
    /*
    @PostMapping("")
    public void addElection(@RequestBody CreateElectionRequest createElectionRequest){
        this.electionService.addElection(createElectionRequest);
    }
    */
    @PutMapping("")
    public void voteCandidateByElectionId(@RequestBody VoteRequest voteRequest){
        this.electionService.voteCandidateByElectionId(voteRequest);
    }


    @GetMapping("/results")
    public List<GetResultResponse> getResults(){
        return electionService.getResults();
    }

    // Handle Election Result

    @GetMapping("/deneme")
    public void getTest(){
        System.out.println("deneme");
    }

}
