package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserValidationRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
