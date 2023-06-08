package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.responses.CandidateGetResponse;
import com.example.onlinevotingsystem.Dto.responses.GetAllCandidateResponse;
import com.example.onlinevotingsystem.services.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private CandidateService candidateService;

    @PostMapping
    public void createCandidate(@RequestBody CreateCandidateRequest createCandidateRequest){
        candidateService.createCandidate(createCandidateRequest);
    }

    @GetMapping("/{candidateId}")
    public CandidateGetResponse getCandidate(@PathVariable Long candidateId){
        return candidateService.getCandidate(candidateId);
    }

    @GetMapping()
    public List<GetAllCandidateResponse> getAllCandidates() {
        return this.candidateService.getAllCandidates();
    }
}
