package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateApplicationRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponse;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseById;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseByUserId;
import com.example.onlinevotingsystem.exceptions.AlreadyApplyApplicationException;
import com.example.onlinevotingsystem.exceptions.InvalidApplicationException;
import com.example.onlinevotingsystem.models.apiModels.ApiSuccessful;
import com.example.onlinevotingsystem.services.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/applications")
@AllArgsConstructor
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
//@CrossOrigin(origins =  "http://localhost:3000")
public class ApplicationController {

    private ApplicationService applicationService;

    @PostMapping("")
    public void createAnApplication(@RequestBody CreateApplicationRequest createApplication) throws AlreadyApplyApplicationException {
        applicationService.createAnApplication(createApplication);
    }
    @GetMapping("")
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }
    @GetMapping("/{applicationId}")
    public ApplicationResponseById getApplicationById(@PathVariable("applicationId") Long applicationId) {
        return applicationService.getApplicationById(applicationId);
    }
    @GetMapping("/user-id/{userId}")
    public ResponseEntity<Object> isAlreadyApplied(@PathVariable("userId") Long userId){
        return this.applicationService.isAlreadyApplied(userId);
    }

    @DeleteMapping("/{applicationId}")
    public void deleteApplicationById(@PathVariable("applicationId") Long applicationId) {
        applicationService.deleteApplicationById(applicationId);

    }

    @PutMapping("")
    public void updateApplicationDetails(@RequestBody UpdateApplicationRequest updateApplicationRequest) throws InvalidApplicationException {
        applicationService.updateApplicationDetails(updateApplicationRequest);
    }

    @GetMapping("/user/{userId}")
    public ApplicationResponseByUserId getApplicationByUserId(@PathVariable("userId") Long userId) {
        return this.applicationService.getApplicationByUserId(userId);
    }

    @PostMapping("/approve/{applicationId}")
    public ResponseEntity<ApiSuccessful> approveApplication(@PathVariable("applicationId") Long applicationId) throws InvalidApplicationException {
        return applicationService.approveApplication(applicationId);
    }


    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<ApiSuccessful> rejectApplication(@PathVariable("applicationId") Long applicationId) throws InvalidApplicationException {
        return applicationService.rejectApplication(applicationId);
    }

    @PostMapping("/upload/user-id/{userId}")
    public ResponseEntity<Object> uploadTranscript(@RequestParam("transcript")MultipartFile multipartTranscriptFile,
                                                   @RequestParam("applicationRequest")MultipartFile multipartApplicationFile,
                                                   @RequestParam("studentCertificate")MultipartFile multipartStudentCertificateFile,
                                                   @RequestParam("political")MultipartFile multipartPoliticalFile,
                                                   @PathVariable("userId") Long userId) {
        return this.applicationService.uploadFiles(multipartTranscriptFile,multipartApplicationFile,multipartStudentCertificateFile,multipartPoliticalFile,userId);
    }

    @GetMapping ("/download-transcript/user-id/{userId}")
    public ResponseEntity<Resource> downloadTranscript(@PathVariable("userId") Long userId){
        return this.applicationService.downloadTranscript(userId);

    }
    @GetMapping ("/download-application/user-id/{userId}")
    public ResponseEntity<Resource> downloadApplication(@PathVariable("userId") Long userId){
        return this.applicationService.downloadApplication(userId);

    }
    @GetMapping ("/download-student-certificate/user-id/{userId}")
    public ResponseEntity<Resource> downloadStudentCertificate(@PathVariable("userId") Long userId){
        return this.applicationService.downloadStudentCertificate(userId);

    }

    @GetMapping ("/download-politicial/user-id/{userId}")
    public ResponseEntity<Resource> downloadPolitical(@PathVariable("userId") Long userId){
        return this.applicationService.downloadPolitical(userId);

    }




}
