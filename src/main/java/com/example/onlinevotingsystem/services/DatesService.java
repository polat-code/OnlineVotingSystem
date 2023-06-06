package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.ApplicationDatesRequest;
import com.example.onlinevotingsystem.models.ApplicationDates;
import com.example.onlinevotingsystem.repository.ApplicationDatesRepository;
import com.example.onlinevotingsystem.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatesService {

    private ApplicationDatesRepository datesRepository;
    public ResponseEntity<Object> setDatesOfApplication(ApplicationDatesRequest datesRequest) {
        if(datesRequest == null || datesRequest.getApplicationStartDate().equals("") || datesRequest.getApplicationFinishDate().equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        ApplicationDates applicationDates = new ApplicationDates().builder()
                .applicationStartDate(datesRequest.getApplicationStartDate())
                .applicationFinishDate(datesRequest.getApplicationFinishDate())
                .build();
        datesRepository.save(applicationDates);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
