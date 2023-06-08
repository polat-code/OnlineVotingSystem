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
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.models.apiModels.ApiSuccessful;
import com.example.onlinevotingsystem.repository.ApplicationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
                .isReview(false)
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
                        .grade(application.getStudent().getGrade())
                        .departmentName(application.getStudent().getDepartment().getDepartmentName())
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
        application.setIsReview(true);
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
        application.setIsReview(true);
        return new ResponseEntity<>(new ApiSuccessful("Successfully application is rejected",HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }


    public ResponseEntity<Object> uploadFiles(MultipartFile multipartTranscriptFile,
                                              MultipartFile multipartApplicationFile,
                                              MultipartFile multipartStudentCertificateFile,
                                              MultipartFile multipartPoliticalFile,
                                              Long userId) {

        ResponseEntity<Object> respTranscript = uploadTranscript(multipartTranscriptFile,userId);
        ResponseEntity<Object> respApplication = uploadApplicationRequestFile(multipartApplicationFile,userId);
        ResponseEntity<Object> respCertificate = uploadStudentCertificate(multipartStudentCertificateFile,userId);
        ResponseEntity<Object> respPolitical = uploadPoliticalFile(multipartPoliticalFile,userId);

        if(respTranscript.getStatusCode().value() == 200
                && respApplication.getStatusCode().value() == 200
                && respCertificate.getStatusCode().value() == 200
                && respPolitical.getStatusCode().value() == 200) {
            return new ResponseEntity<>("All files have been uploaded",HttpStatus.OK);
        }else if (respTranscript.getStatusCode().value() != 200){
            return respTranscript;
        }else if(respApplication.getStatusCode().value() != 200) {
            return respApplication;
        }else if(respPolitical.getStatusCode().value() != 200){
            return respPolitical;
        }else if (respCertificate.getStatusCode().value() != 200){
            return respCertificate;
        }else {
            return new ResponseEntity<>("There is a undefined error!",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private ResponseEntity<Object> uploadPoliticalFile(MultipartFile multipartPoliticalFile, Long userId) {
        Path path = Paths.get("./images/politicals/userId-" + userId + ".pdf") ;

        try {
            Files.createDirectories(path);
            Files.copy(multipartPoliticalFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return  new ResponseEntity<>("student Certificate : " + e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

    }

    private ResponseEntity<Object> uploadStudentCertificate(MultipartFile multipartStudentCertificateFile, Long userId) {
        Path path = Paths.get("./images/studentCertificates/userId-" + userId + ".pdf") ;

        try {
            Files.createDirectories(path);
            Files.copy(multipartStudentCertificateFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return  new ResponseEntity<>("student Certificate : " + e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

    }

    private ResponseEntity<Object> uploadApplicationRequestFile(MultipartFile multipartApplicationFile, Long userId) {
        Path path = Paths.get("./images/applicationRequests/userId-" + userId + ".pdf") ;

        try {
            Files.createDirectories(path);
            Files.copy(multipartApplicationFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return  new ResponseEntity<>("application request :" + e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

    }

    private ResponseEntity<Object> uploadTranscript(MultipartFile multipartTranscriptFile,Long userId) {
        Path path = Paths.get("./images/transcripts/userId-" + userId + ".pdf") ;

        try {
            Files.createDirectories(path);
            Files.copy(multipartTranscriptFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e);
            return  new ResponseEntity<>("transcript : " + e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<Resource> downloadTranscript(Long userId) {
        File file = new File("./images/transcripts/userId-" + userId + ".pdf");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transcript.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource  = null;
        try{
            resource = new ByteArrayResource(Files.readAllBytes(path));
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    public ResponseEntity<Resource> downloadApplication(Long userId) {
        File file = new File("./images/applicationRequests/userId-" + userId + ".pdf");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=applicationRequest.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource  = null;
        try{
            resource = new ByteArrayResource(Files.readAllBytes(path));
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    public ResponseEntity<Resource> downloadStudentCertificate(Long userId) {
        File file = new File("./images/studentCertificates/userId-" + userId + ".pdf");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=studentCertificate.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource  = null;
        try{
            resource = new ByteArrayResource(Files.readAllBytes(path));
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    public ResponseEntity<Resource> downloadPolitical(Long userId) {
        File file = new File("./images/politicals/userId-" + userId + ".pdf");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=political.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource  = null;
        try{
            resource = new ByteArrayResource(Files.readAllBytes(path));
        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
