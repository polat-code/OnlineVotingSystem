package com.example.onlinevotingsystem.Dto.requests;

import com.example.onlinevotingsystem.models.Application;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {
    private String name;
    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    private String email;


    @Column(name = "grade")
    private int grade;


    private String studentNumber;

    private String departmentName;


}
