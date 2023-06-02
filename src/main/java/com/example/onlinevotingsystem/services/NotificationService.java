package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateNotificationRequest;
import com.example.onlinevotingsystem.models.Candidate;
import com.example.onlinevotingsystem.models.Election;
import com.example.onlinevotingsystem.models.Notification;
import com.example.onlinevotingsystem.repository.ElectionRepository;
import com.example.onlinevotingsystem.repository.NotificationRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NotificationService {


    private JavaMailSender mailSender;

    private NotificationRepository notificationRepository;

    private StudentRepository studentRepository;

    private ElectionRepository electionRepository;


    public void createNotification(CreateNotificationRequest createNotificationRequest) {

        Notification notification = Notification.builder()
                .description(createNotificationRequest.getDescription())
                .title(createNotificationRequest.getTitle()).build();

        sendEmail(notification);

        notificationRepository.save(notification);
    }



    public void createResultNotification() {
        List<Election> elections = electionRepository.findAll();
        StringBuilder text = new StringBuilder();
        for (Election election : elections){
            List<Candidate> candidates = election.getCandidates();
            Map<String,Integer> result = new LinkedHashMap<>();
            Collections.sort(candidates, (c1, c2) -> c2.getVoteCount() - c1.getVoteCount());
            for (Candidate candidate : candidates){
                String nameSurname = candidate.getStudent().getName() + " " + candidate.getStudent().getSurname();
                Integer voteCount = candidate.getVoteCount();
                result.put(nameSurname,voteCount);


            }
            text.append(election.getDepartment().getDepartmentName()).append(" ").append(result).append("\n");


        }
        Notification notification = Notification.builder().title("Results").description(text.toString()).build();
        notificationRepository.save(notification);
        sendEmail(notification);
    }


    private void sendEmail(Notification notification){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(notification.getTitle());
        simpleMailMessage.setText(notification.getDescription());

        List<String> studentEmails= this.studentRepository.getAllEmails();
        for (String email : studentEmails){
            if (!email.equals("")) {
                simpleMailMessage.setTo(email);
                mailSender.send(simpleMailMessage);
            }
        }

    }

}