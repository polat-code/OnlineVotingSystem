package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.IsValidEmailRequest;
import com.example.onlinevotingsystem.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private AuthService authService;
    @PostMapping("/isvalid")
    public ResponseEntity<Object> isValidEmailOrNot(@RequestBody IsValidEmailRequest validEmailRequest) {
       return this.authService.isValidEmailOrNot(validEmailRequest);
    }


}
