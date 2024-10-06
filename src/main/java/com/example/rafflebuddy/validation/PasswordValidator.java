package com.example.rafflebuddy.validation;

import com.example.rafflebuddy.exception.IllegalArgumentsException;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public void validate(String password) {
        if (password == null || password.length() < 6) { // Example validation
            throw new IllegalArgumentsException("Password must be at least 6 characters long");
        }
        // Add more rules as needed (e.g., check for special characters, numbers, etc.)
    }
}
