package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.LoginRequest;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private StudentRepository studentRepository;

    public void login(LoginRequest loginRequest) {


        Student foundUserByEmail = studentRepository.findByEmail(loginRequest.getEmail());

        if(foundUserByEmail.getPassword().matches(loginRequest.getPassword())){
            System.out.println("Succesfully");
        }
        else
            System.out.println("There is no user with that password , please check your email and password");









    }
}
