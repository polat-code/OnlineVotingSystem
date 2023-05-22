package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.services.ApplicationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    @PutMapping("")
    public void createAnApplication(@RequestBody CreateApplicationRequest createApplication) {
        applicationService.createAnApplication(createApplication);
    }

}
