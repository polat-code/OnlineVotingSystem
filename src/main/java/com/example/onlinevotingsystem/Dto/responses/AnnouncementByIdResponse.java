package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AnnouncementByIdResponse {

    private String title;
    private String description;

    public AnnouncementByIdResponse() {
    }
}
