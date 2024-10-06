package com.example.rafflebuddy.controller;

import com.example.rafflebuddy.dto.*;
import com.example.rafflebuddy.model.User;
import com.example.rafflebuddy.service.imp.AuthenticationService;
import com.example.rafflebuddy.service.imp.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthorizationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<RegisterResponseDto>> register (@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder()
                .id(registeredUser.getId())
                .fullName(registeredUser.getFullName())
                .email(registeredUser.getEmail())
                .createdAt(registeredUser.getCreatedAt())
                .raffles(registeredUser.getRaffles())
                .credentialsNonExpired(registeredUser.isCredentialsNonExpired())
                .username(registeredUser.getUsername())
                .build();
        ResponseDTO<RegisterResponseDto> responseDTO = ResponseDTO.<RegisterResponseDto>builder()
                .data(registerResponseDto)
                .message("Registration successful")
                .success(true)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDto>> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getExpirationTime());

        ResponseDTO<LoginResponseDto> responseDTO = ResponseDTO.<LoginResponseDto>builder()
                .message("Login Success")
                .data(loginResponseDto)
                .success(true)
                .build();
        return  ResponseEntity.ok(responseDTO);
    }
}
