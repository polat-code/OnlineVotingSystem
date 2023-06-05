package com.example.onlinevotingsystem.Dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ValidateEmailResponse {

    private boolean isValid;


}
