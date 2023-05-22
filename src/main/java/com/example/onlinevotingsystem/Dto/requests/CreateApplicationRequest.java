package com.example.onlinevotingsystem.Dto.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CreateApplicationRequest {
    @NonNull
    private String transcriptPath;
    @NonNull
    private String applicationRequest;
    @NonNull
    private String studentCertificate;
    @NonNull
    private String political;

    @NonNull
    private Long userId;

}
