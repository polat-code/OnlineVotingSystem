package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateNotificationRequest;
import com.example.onlinevotingsystem.models.Notification;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.NotificationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {


    private JavaMailSender mailSender;

    private NotificationRepository notificationRepository;

    private StudentRepository studentRepository;


    public void createNotification(CreateNotificationRequest createNotificationRequest) {

        Notification notification = Notification.builder()
                .description(createNotificationRequest.getDescription())
                .title(createNotificationRequest.getTitle()).build();

        sendEmail(notification);

        notificationRepository.save(notification);
    }

    private void sendEmail(Notification notification){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(notification.getTitle());
        simpleMailMessage.setText(notification.getDescription());

        List<String> studentEmails= this.studentRepository.getAllEmails();
        for (String email : studentEmails){
            System.out.println(email);
            simpleMailMessage.setTo(email);
            mailSender.send(simpleMailMessage);
        }

    }



}