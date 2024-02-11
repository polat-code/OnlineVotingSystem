package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCandidateResponse {

    private Long userId;
    private String name;
    private String surname;
    private String departmentName;
    private int grade;

}
