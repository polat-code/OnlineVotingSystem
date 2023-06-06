package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.IsValidEmailRequest;
import com.example.onlinevotingsystem.Dto.requests.LoginRequest;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private StudentRepository studentRepository;


    public ResponseEntity<Object> isValidEmailOrNot(IsValidEmailRequest validEmailRequest) {
        Student student = studentRepository.findByEmail(validEmailRequest.getEmail());
        if(student != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
