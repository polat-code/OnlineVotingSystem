package com.example.onlinevotingsystem.controllers;
import com.example.onlinevotingsystem.Dto.requests.CreateNotificationRequest;
import com.example.onlinevotingsystem.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping
    public void createNotification(@RequestBody CreateNotificationRequest createNotificationRequest){
        notificationService.createNotification(createNotificationRequest);
    }

    @PostMapping("/sendResults")
    public void createResultNotification(){
        notificationService.createResultNotification();
    }
}
