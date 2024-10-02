package com.example.rafflebuddy.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Consolation {
    private String name;
    private String description;
}
