package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VotingValidationResponse {
    @NotNull
    private boolean isAlreadyVoted;
}
