package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class VoteRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long candidateId;

    @NotNull
    private Long electionId;
}
