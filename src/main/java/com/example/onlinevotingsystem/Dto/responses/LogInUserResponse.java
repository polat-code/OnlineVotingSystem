package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInUserResponse {

    private Long userId;
    private String name;
    private String surname;
    private String studentNumber;
    private int grade;
    private String email;
    private Boolean isAdmin;
}
