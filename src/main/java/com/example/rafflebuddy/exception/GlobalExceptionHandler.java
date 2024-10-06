package com.example.rafflebuddy.exception;

import com.example.rafflebuddy.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<Object>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ResponseDTO<Object> response = ResponseDTO.<Object>builder()
                .message(ex.getMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentsException.class)
    public ResponseEntity<ResponseDTO<Object>> handleIllegalExceptions(IllegalArgumentsException ex) {
        ResponseDTO<Object> response = ResponseDTO.<Object>builder()
                .message(ex.getMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleSecurityException(Exception exception) {
        String errorMessage = "Unknown internal server error.";

        if (exception instanceof BadCredentialsException) errorMessage = "The username or password is incorrect";
        if (exception instanceof AccountStatusException) errorMessage = "The account is locked";
        if (exception instanceof AccessDeniedException)  errorMessage = "You are not authorized to access this resource";
        if (exception instanceof SignatureException) errorMessage = "The JWT signature is invalid";
        if (exception instanceof ExpiredJwtException) errorMessage = "The JWT token has expired";
        if(exception instanceof SQLIntegrityConstraintViolationException) errorMessage ="User Exists";
        ResponseDTO<Object> response = ResponseDTO.builder()
                .success(false)
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}