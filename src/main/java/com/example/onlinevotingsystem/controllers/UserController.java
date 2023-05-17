package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.UserValidationRequest;
import com.example.onlinevotingsystem.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("")
    public User validateUser(@RequestBody UserValidationRequest userValidationRequest) {
        return null;
    }


}
