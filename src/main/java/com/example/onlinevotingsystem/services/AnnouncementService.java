package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.AnnouncementRequest;
import com.example.onlinevotingsystem.Dto.responses.AnnouncementByIdResponse;
import com.example.onlinevotingsystem.Dto.responses.AnnouncementResponse;
import com.example.onlinevotingsystem.models.Announcement;
import com.example.onlinevotingsystem.models.apiModels.ApiError;
import com.example.onlinevotingsystem.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private AnnouncementRepository announcementRepository;


    // Returns 406 status code if it is not valid otherwise, it returns 200
    public ResponseEntity<Object> makeAnnouncement(AnnouncementRequest announcementRequest) {
        if(announcementRequest.getTitle().equals("") || announcementRequest.getDescription().equals("") ){
            new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Announcement announcement = new Announcement().builder()
                .title(announcementRequest.getTitle())
                .description(announcementRequest.getDescription())
                .build();
        announcementRepository.save(announcement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements =  this.announcementRepository.findAll();
        List<AnnouncementResponse> announcementResponses = new ArrayList<>();
        for(Announcement announcement : announcements) {
            AnnouncementResponse announcementResponse = new AnnouncementResponse().builder()
                    .announcementId(announcement.getAnnouncementId())
                    .description(announcement.getDescription())
                    .title(announcement.getTitle())
                    .build();
            announcementResponses.add(announcementResponse);
        }

        return announcementResponses;
    }

    public AnnouncementByIdResponse getAnnouncementById(Long announcementId) {
        Announcement announcement = this.announcementRepository.findById(announcementId).orElseThrow();
        AnnouncementByIdResponse announcementByIdResponse = new AnnouncementByIdResponse().builder()
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .build();

        return announcementByIdResponse;

    }
}
