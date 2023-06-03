package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.AuthenticationRequest;
import com.example.onlinevotingsystem.Dto.requests.RegisterRequest;
import com.example.onlinevotingsystem.Dto.responses.AuthenticationResponse;
import com.example.onlinevotingsystem.models.User;
import com.example.onlinevotingsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private UserRepository repository;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail()
                ,request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
