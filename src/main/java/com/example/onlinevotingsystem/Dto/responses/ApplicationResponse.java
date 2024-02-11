package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

    private Long applicationId;
    private Long userId;
    private String transcriptPath;
    private String applicationRequest;

    private String studentCertificate;

    private String political;

    private String studentName;

    private String studentSurname;

    private String studentNumber;
    private String departmentName;
    private int grade;
}
