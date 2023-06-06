package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IsValidEmailRequest {

    private String email;

    public IsValidEmailRequest() {
    }
}
