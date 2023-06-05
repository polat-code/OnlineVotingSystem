package com.example.onlinevotingsystem.Dto.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String name;

    private String surname;

    private String email;

    private int grade;

    private String studentNumber;

    private String departmentName;

    private String password;
}
