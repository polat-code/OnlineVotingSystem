package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.LoginRequest;


import com.example.onlinevotingsystem.services.AuthService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
   @GetMapping("/login")
   public void login (@RequestBody LoginRequest loginRequest){
       authService.login(loginRequest);
   }



}

