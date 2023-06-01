package com.example.onlinevotingsystem.models.apiModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiSuccessful {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
}
