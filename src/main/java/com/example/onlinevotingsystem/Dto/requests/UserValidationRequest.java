package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor

public class UserValidationRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
