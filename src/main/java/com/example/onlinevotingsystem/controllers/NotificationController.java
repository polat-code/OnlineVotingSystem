package com.example.onlinevotingsystem.controllers;
import com.example.onlinevotingsystem.Dto.requests.CreateNotificationRequest;
import com.example.onlinevotingsystem.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Object> createNotification(@RequestBody CreateNotificationRequest createNotificationRequest){
        notificationService.createNotification(createNotificationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendResults")
    public void createResultNotification(){
        notificationService.createResultNotification();
    }
}
