package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.IsValidEmailRequest;
import com.example.onlinevotingsystem.Dto.requests.LogInUserRequest;
import com.example.onlinevotingsystem.Dto.requests.RegistrationRequest;
import com.example.onlinevotingsystem.Dto.requests.SaveUserRequest;
import com.example.onlinevotingsystem.Dto.responses.LogInUserResponse;
import com.example.onlinevotingsystem.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
public class AuthController {
    private AuthService authService;

    @PostMapping("/isvalid")
    public ResponseEntity<Object> isValidEmailOrNot(@RequestBody IsValidEmailRequest validEmailRequest) {
       return this.authService.isValidEmailOrNot(validEmailRequest);
    }



    @PostMapping("/register")
    public ResponseEntity<Object> registerStudent(@RequestBody RegistrationRequest registrationRequest) {
        return this.authService.registerStudent(registrationRequest);
    }

    @PostMapping("/log-in")
    public LogInUserResponse getUserByEmail(@RequestBody LogInUserRequest logInUserRequest) {
        return  this.authService.getUserByEmail(logInUserRequest);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody SaveUserRequest saveUserRequest) {
        return this.authService.saveUser(saveUserRequest);
    }



}
