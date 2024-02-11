package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApplicationResponseById {

    private Long applicationId;
    private Boolean is_approved;
    private Boolean is_reviewed;
    private String transcriptPath;
    private String applicationRequest;

    private String studentCertificate;

    private String political;
}
