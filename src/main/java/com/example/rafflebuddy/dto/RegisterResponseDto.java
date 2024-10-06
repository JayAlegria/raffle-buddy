package com.example.rafflebuddy.dto;

import com.example.rafflebuddy.model.Raffle;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class RegisterResponseDto {
    private long id;
    private String fullName;
    private String email;
    private Date createdAt;
    private List<Raffle> raffles;
    private boolean credentialsNonExpired;
    private String username;
}
