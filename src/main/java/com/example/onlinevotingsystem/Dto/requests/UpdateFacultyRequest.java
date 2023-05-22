package com.example.onlinevotingsystem.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFacultyRequest {

    @NotNull
    @NotBlank
    @Size(min = 4)
    private String facultyName;

}
