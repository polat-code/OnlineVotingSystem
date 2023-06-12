package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.AnnouncementRequest;
import com.example.onlinevotingsystem.Dto.responses.AnnouncementByIdResponse;
import com.example.onlinevotingsystem.Dto.responses.AnnouncementResponse;
import com.example.onlinevotingsystem.services.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
@AllArgsConstructor
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
//@CrossOrigin(origins =  "http://localhost:3000")
public class AnnouncementController {




    private AnnouncementService announcementService;
    @PostMapping()
    public ResponseEntity<Object> makeAnnouncement(@RequestBody AnnouncementRequest announcementRequest){
        return this.announcementService.makeAnnouncement(announcementRequest);
    }

    @GetMapping
    public List<AnnouncementResponse> getAllAnnouncements(){
        return  this.announcementService.getAllAnnouncements();
    }

    @GetMapping("/{announcementId}")
    public AnnouncementByIdResponse getAnnouncementById(@PathVariable("announcementId") Long announcementId ){
        return this.announcementService.getAnnouncementById(announcementId);

    }
}
