package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.ValidateEmailRequest;
import com.example.onlinevotingsystem.Dto.requests.AuthenticationRequest;
import com.example.onlinevotingsystem.Dto.requests.RegisterRequest;
import com.example.onlinevotingsystem.Dto.responses.AuthenticationResponse;
import com.example.onlinevotingsystem.Dto.responses.ValidateEmailResponse;
import com.example.onlinevotingsystem.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){

        return ResponseEntity.ok(authenticationService.register(registerRequest));

    }

    @PostMapping("/validateEmail")
    public ResponseEntity<ValidateEmailResponse> isValid(@RequestBody ValidateEmailRequest validateEmailRequest){

        return ResponseEntity.ok(authenticationService.isValid(validateEmailRequest));
    }




    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
