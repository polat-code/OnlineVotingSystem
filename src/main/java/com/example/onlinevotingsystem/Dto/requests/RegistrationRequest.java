package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String name;

    private String surname;

    private String email;
    private int grade;
    private String studentNumber;
    private Boolean isAdmin;
    private String departmentName;
}
