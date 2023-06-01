package com.example.onlinevotingsystem.Dto.responses;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllApplicantsResponse {
    @NotNull
    private Long candidateId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String profilePhotoPath;
    @NotNull
    private int grade;
}
