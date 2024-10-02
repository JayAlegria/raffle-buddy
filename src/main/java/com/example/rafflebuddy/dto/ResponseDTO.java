package com.example.rafflebuddy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
    private T data;
    private boolean success;
    private String message;
}
