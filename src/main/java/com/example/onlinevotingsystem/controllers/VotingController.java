package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.responses.GetAllApplicantsResponse;
import com.example.onlinevotingsystem.Dto.responses.VotingValidationResponse;
import com.example.onlinevotingsystem.models.apiModels.ApiSuccessful;
import com.example.onlinevotingsystem.services.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/voting")
@AllArgsConstructor
public class VotingController {

    private VotingService votingService;

    //check if you have already voted
    @GetMapping("/validation/{userId}")
    public VotingValidationResponse validateUserAlreadyVoteOrNot(@PathVariable("userId") Long userId){
        return this.votingService.validateUserAlreadyVoteOrNot(userId);
    }


    // Make a query to return all applicants by user id
    // Output is nonsense here.
    @GetMapping("/applicants/{userId}")
    public List<GetAllApplicantsResponse> getAllApplicants(@PathVariable("userId") Long userId){
        return this.votingService.getAllApplicants(userId);
    }
    // voting




}