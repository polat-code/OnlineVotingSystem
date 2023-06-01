package com.example.onlinevotingsystem.Dto.responses;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ApplicationResponseByUserId {


    private Long applicationId;

    private String transcriptPath;

    private String applicationRequest;

    private String studentCertificate;

    private String political;

    private Long userId;

    @NonNull
    private Boolean isSubmittedApplication;

    public ApplicationResponseByUserId() {

    }

    @Override
    public String toString() {
        return "ApplicationResponseByUserId{" +
                "applicationId=" + applicationId +
                ", transcriptPath='" + transcriptPath + '\'' +
                ", applicationRequest='" + applicationRequest + '\'' +
                ", studentCertificate='" + studentCertificate + '\'' +
                ", political='" + political + '\'' +
                ", userId=" + userId +
                ", isSubmittedApplication=" + isSubmittedApplication +
                '}';
    }
}
