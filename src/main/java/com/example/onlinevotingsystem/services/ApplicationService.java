package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.Dto.requests.CreateCandidateRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateApplicationRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponse;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseById;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseByUserId;
import com.example.onlinevotingsystem.exceptions.AlreadyApplyApplicationException;
import com.example.onlinevotingsystem.exceptions.InvalidApplicationException;
import com.example.onlinevotingsystem.models.Application;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.models.apiModels.ApiSuccessful;
import com.example.onlinevotingsystem.repository.ApplicationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;

    private StudentRepository userRepository;

    private CandidateService candidateService;
    public void createAnApplication(CreateApplicationRequest createApplication) throws AlreadyApplyApplicationException {
        Student student = (Student) userRepository.findById(createApplication.getUserId()).get();
        Application alreadyApplyApplication = applicationRepository.findApplicationByUserId(createApplication.getUserId());
        if(alreadyApplyApplication != null) {
            throw new AlreadyApplyApplicationException("There is already an application with user Id : " + createApplication.getUserId());
        }
        Application application = new Application().builder()
                .transcriptPath(createApplication.getTranscriptPath())
                .applicationRequest(createApplication.getApplicationRequest())
                .studentCertificate(createApplication.getStudentCertificate())
                .political(createApplication.getPolitical())
                .student(student)
                .isApproved(false)
                .build();
        applicationRepository.save(application);
    }

    public List<ApplicationResponse> getAllApplications() {
        List<ApplicationResponse> applicationResponses = new ArrayList<>();
        List<Application> applications = applicationRepository.findAll();
        if(applications.size() > 0) {
            for(Application application : applications) {
                ApplicationResponse applicationResponse = new ApplicationResponse().builder()
                        .applicationId(application.getApplicationId())
                        .studentCertificate(application.getStudentCertificate())
                        .applicationRequest(application.getApplicationRequest())
                        .transcriptPath(application.getTranscriptPath())
                        .political(application.getPolitical())
                        .studentName(application.getStudent().getName())
                        .studentSurname(application.getStudent().getSurname())
                        .studentNumber(application.getStudent().getStudentNumber())
                        .build();
                applicationResponses.add(applicationResponse);
            }

        }
        return applicationResponses;

    }

    public void deleteApplicationById(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            try {
                throw  new InvalidApplicationException("There is no such application to delete");
            } catch (InvalidApplicationException e) {
                throw new RuntimeException(e);
            }
        });

        applicationRepository.deleteById(applicationId);

    }

    public void updateApplicationDetails(UpdateApplicationRequest updateApplicationRequest) throws InvalidApplicationException {
        System.out.println(updateApplicationRequest.getApplicationId());
        Application application = applicationRepository.findById(updateApplicationRequest.getApplicationId()).orElseThrow();
        if(application == null) {
            throw new InvalidApplicationException("There is no such an application id : " + updateApplicationRequest.getApplicationId());
        }
        System.out.println(updateApplicationRequest);
        // If only someting is missing then old data have to stay same.
        if(updateApplicationRequest.getTranscriptPath() != null ) {
            application.setTranscriptPath(updateApplicationRequest.getApplicationRequest());
        }
        if (updateApplicationRequest.getApplicationRequest() != null) {
            application.setApplicationRequest(updateApplicationRequest.getApplicationRequest());
        }
        if(updateApplicationRequest.getStudentCertificate() != null) {
            application.setStudentCertificate(updateApplicationRequest.getStudentCertificate());
        }
        if(updateApplicationRequest.getPolitical() != null) {
            application.setPolitical(updateApplicationRequest.getPolitical());
        }
        applicationRepository.save(application);
    }

    public ApplicationResponseById getApplicationById(Long applicationId) {

        Application application = applicationRepository.findById(applicationId).orElseThrow(
                () -> {
                    try {
                        throw new InvalidApplicationException("There is no such application to update");
                    } catch (InvalidApplicationException e) {
                        throw new RuntimeException(e);
                    }
                });

        ApplicationResponseById applicationResponseById = new ApplicationResponseById().builder()
                .applicationId(application.getApplicationId())
                .applicationRequest(application.getApplicationRequest())
                .transcriptPath(application.getTranscriptPath())
                .political(application.getPolitical())
                .studentCertificate(application.getStudentCertificate())
                .build();
        return applicationResponseById;
    }


    public ApplicationResponseByUserId getApplicationByUserId(Long userId) {
        Application application = this.applicationRepository.findApplicationByUserId(userId);
        if(application == null) {
            return new ApplicationResponseByUserId().builder().isSubmittedApplication(false).build();
        }
        ApplicationResponseByUserId applicationResponseByUserId = new ApplicationResponseByUserId().builder()
                .applicationId(application.getApplicationId())
                .applicationRequest(application.getApplicationRequest())
                .transcriptPath(application.getTranscriptPath())
                .political(application.getPolitical())
                .studentCertificate(application.getStudentCertificate())
                .userId(application.getStudent().getUserId())
                .isSubmittedApplication(true)
                .build();

        System.out.println(applicationResponseByUserId);

        return applicationResponseByUserId;

    }

    public ResponseEntity<ApiSuccessful> approveApplication(Long applicationId) throws InvalidApplicationException {

        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            try {
                throw new InvalidApplicationException("There is no such a applicationId : " + applicationId);
            } catch (InvalidApplicationException e) {
                throw new RuntimeException(e);
            }
        });

        application.setIsApproved(true);
        candidateService.createCandidate(new CreateCandidateRequest().builder()
                        .studentId(application.getStudent().getUserId())
                        .build());

        applicationRepository.save(application);

        return  new ResponseEntity<>(new ApiSuccessful("Sucessfully approved", HttpStatus.OK, LocalDateTime.now()),HttpStatus.OK);



    }

    public ResponseEntity<ApiSuccessful> rejectApplication(Long applicationId) throws InvalidApplicationException{
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            try {
                throw new InvalidApplicationException("There is no such an application Id : " + applicationId);
            } catch (InvalidApplicationException e) {
                throw new RuntimeException(e);
            }
        });

        application.setIsApproved(false);
        return new ResponseEntity<>(new ApiSuccessful("Successfully application is rejected",HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
}
