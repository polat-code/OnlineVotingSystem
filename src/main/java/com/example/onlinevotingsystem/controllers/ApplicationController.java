package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateApplicationRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponse;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseById;
import com.example.onlinevotingsystem.models.Application;
import com.example.onlinevotingsystem.services.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    @PostMapping("")
    public void createAnApplication(@RequestBody CreateApplicationRequest createApplication) {
        applicationService.createAnApplication(createApplication);
    }
    @GetMapping("")
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }
    @GetMapping("/{applicationId}")
    public ApplicationResponseById getApplicationById(@PathVariable Long applicationId) {
        return applicationService.getApplicationById(applicationId);
    }

    @DeleteMapping("/{applicationId}")
    public void deleteApplicationById(@PathVariable Long applicationId) {
        applicationService.deleteApplicationById(applicationId);

    }
    @PutMapping("")
    public void updateApplicationDetails(@RequestBody UpdateApplicationRequest updateApplicationRequest) {
        applicationService.updateApplicationDetails(updateApplicationRequest);
    }

}
