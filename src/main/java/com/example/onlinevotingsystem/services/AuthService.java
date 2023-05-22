package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.LoginRequest;
import com.example.onlinevotingsystem.models.User;
import com.example.onlinevotingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    public void login(LoginRequest loginRequest) {


        User foundUserByEmail = userRepository.findByEmail(loginRequest.getEmail());

        if(foundUserByEmail.getPassword().matches(loginRequest.getPassword())){
            System.out.println("Succesfully");
        }
        else
            System.out.println("There is no user with that password , please check your email and password");














    }
}
