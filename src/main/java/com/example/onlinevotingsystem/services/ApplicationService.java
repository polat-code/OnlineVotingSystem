package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateApplicationRequest;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponse;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseById;
import com.example.onlinevotingsystem.Dto.responses.ApplicationResponseByUserId;
import com.example.onlinevotingsystem.exceptions.AlreadyApplyApplicationException;
import com.example.onlinevotingsystem.exceptions.InvalidApplicationException;
import com.example.onlinevotingsystem.models.Application;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.ApplicationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;

    private StudentRepository userRepository;
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

    public void updateApplicationDetails(UpdateApplicationRequest updateApplicationRequest) {
        Application application = applicationRepository.findById(updateApplicationRequest.getApplicationId()).orElseThrow(
                () -> {
                    try {
                        throw  new InvalidApplicationException("There is no such application to update");
                    } catch (InvalidApplicationException e) {
                        throw new RuntimeException(e);
                    }
                });

        // If only someting is missing then old data have to stay same.
        application.setTranscriptPath(updateApplicationRequest.getTranscriptPath() != null ? updateApplicationRequest.getApplicationRequest() : application.getTranscriptPath());
        application.setApplicationRequest(updateApplicationRequest.getApplicationRequest() != null ? updateApplicationRequest.getApplicationRequest() : application.getApplicationRequest());
        application.setStudentCertificate(updateApplicationRequest.getStudentCertificate() != null ? updateApplicationRequest.getStudentCertificate() : application.getStudentCertificate()); ;
        application.setPolitical(updateApplicationRequest.getPolitical() != null ? updateApplicationRequest.getPolitical() : application.getPolitical());

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
}
