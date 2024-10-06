package com.example.rafflebuddy.service.imp;

import com.example.rafflebuddy.dto.LoginUserDto;
import com.example.rafflebuddy.dto.RegisterUserDto;
import com.example.rafflebuddy.exception.UserAlreadyExistsException;
import com.example.rafflebuddy.model.User;
import com.example.rafflebuddy.repository.UserRepository;
import com.example.rafflebuddy.validation.EmailValidator;
import com.example.rafflebuddy.validation.PasswordValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, EmailValidator emailValidator, PasswordValidator passwordValidator
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    public User signup(RegisterUserDto input) {

        if(userRepository.existsByEmail(input.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + input.getEmail() + " already exists");
        }

        emailValidator.validate(input.getEmail());
        passwordValidator.validate(input.getPassword());

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}