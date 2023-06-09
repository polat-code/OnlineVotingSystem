package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.ApplicationDatesRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationDateResponse;
import com.example.onlinevotingsystem.Dto.responses.ElectionDateResponse;
import com.example.onlinevotingsystem.models.ApplicationDates;
import com.example.onlinevotingsystem.models.ElectionDate;
import com.example.onlinevotingsystem.repository.ApplicationDatesRepository;
import com.example.onlinevotingsystem.repository.ElectionDateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DatesService {

    private ApplicationDatesRepository applicationDatesRepository;
    private ElectionDateRepository electionDateRepository;
    public ResponseEntity<Object> setDatesOfApplication(ApplicationDatesRequest datesRequest) {
        if(datesRequest == null || datesRequest.getApplicationStartDate().equals("") || datesRequest.getApplicationFinishDate().equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        ApplicationDates applicationDates = new ApplicationDates().builder()
                .applicationStartDate(datesRequest.getApplicationStartDate())
                .applicationFinishDate(datesRequest.getApplicationFinishDate())
                .createdAt(LocalDateTime.now().toString())
                .build();
        applicationDatesRepository.save(applicationDates);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ElectionDateResponse> getAllElectionDates() {
        List<ElectionDate> electionDates = this.electionDateRepository.findAll();
        if(electionDates.isEmpty()) {
            return new ArrayList<>();
        }else {
            List<ElectionDateResponse> electionDateResponses = new ArrayList<>();
            for(ElectionDate electionDate: electionDates) {
                ElectionDateResponse electionDateResponse = new ElectionDateResponse().builder()
                        .electionStartDate(electionDate.getElectionStartDate())
                        .electionFinishDate(electionDate.getElectionFinishDate())
                        .createdAt(electionDate.getCreatedAt())
                        .build();
                electionDateResponses.add(electionDateResponse);
            }
            return electionDateResponses;
        }
    }

    public List<ApplicationDateResponse> getAllApplicationDates() {
        List<ApplicationDates> applicationDates = this.applicationDatesRepository.findAll();
        if(applicationDates.isEmpty()) {
            return new ArrayList<>();
        }else {
            List<ApplicationDateResponse> applicationDateResponse = new ArrayList<>();
            for(ApplicationDates applicationDate: applicationDates) {
                ApplicationDateResponse electionDateResponse = new ApplicationDateResponse().builder()
                        .applicationStartDate(applicationDate.getApplicationStartDate())
                        .applicationFinishDate(applicationDate.getApplicationFinishDate())
                        .createdAt(applicationDate.getCreatedAt())
                        .build();
                applicationDateResponse.add(electionDateResponse);
            }
            return applicationDateResponse;
        }
    }
}
