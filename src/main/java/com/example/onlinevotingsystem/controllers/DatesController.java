package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.ApplicationDatesRequest;
import com.example.onlinevotingsystem.Dto.requests.CreateElectionRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationDateResponse;
import com.example.onlinevotingsystem.Dto.responses.ElectionDateResponse;
import com.example.onlinevotingsystem.models.ApplicationDates;
import com.example.onlinevotingsystem.services.ApplicationService;
import com.example.onlinevotingsystem.services.DatesService;
import com.example.onlinevotingsystem.services.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dates")
@CrossOrigin(origins = "http://localhost:3000")
public class DatesController {

    private DatesService datesService;

    private ElectionService electionService;
    @PostMapping("/application")
    public ResponseEntity<Object> setDatesOfApplication(@RequestBody ApplicationDatesRequest datesRequest) {
        return  this.datesService.setDatesOfApplication(datesRequest);

    }

    @PostMapping("/election")
    public ResponseEntity<Object> addElection(@RequestBody CreateElectionRequest createElectionRequest){
        return this.electionService.addElection(createElectionRequest);
    }

    @GetMapping("/elections")
    public List<ElectionDateResponse> getAllElectionDates() {
        return  this.datesService.getAllElectionDates();
    }

    @GetMapping("/applications")
    public List<ApplicationDateResponse> getAllApplicationDates() {
        return  this.datesService.getAllApplicationDates();
    }
}
