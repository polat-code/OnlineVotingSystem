package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateApplicationRequest;
import com.example.onlinevotingsystem.models.Application;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.ApplicationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private ApplicationRepository applicationRepository;

    private StudentRepository userRepository;
    public void createAnApplication(CreateApplicationRequest createApplication) {
        Student student = (Student) userRepository.findById(createApplication.getUserId()).get();
        Application application = new Application().builder()
                .transcriptPath(createApplication.getTranscriptPath())
                .applicationRequest(createApplication.getApplicationRequest())
                .studentCertificate(createApplication.getStudentCertificate())
                .political(createApplication.getPolitical())
                .student(student)
                .build();
        applicationRepository.save(application);
    }
}
