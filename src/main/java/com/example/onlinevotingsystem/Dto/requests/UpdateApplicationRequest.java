package com.example.onlinevotingsystem.Dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UpdateApplicationRequest {

    @NonNull
    private Long applicationId;

    private String transcriptPath;

    private String applicationRequest;

    private String studentCertificate;

    private String political;

}
