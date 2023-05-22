package com.example.onlinevotingsystem.Dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreateElectionRequest {

    @NotNull
    private Long departmentId;

    @NotNull
    @NotBlank
    private String electionName;

    @NotNull
    @NotBlank
    private String electionDate;



}
