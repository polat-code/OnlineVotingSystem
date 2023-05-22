package com.example.onlinevotingsystem.Dto.responses;

import com.example.onlinevotingsystem.models.Student;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private String transcriptPath;
    private String applicationRequest;

    private String studentCertificate;

    private String political;

    private String studentName;

    private String studentSurname;

    private String studentNumber;
}
