package com.example.rafflebuddy.enums;

public enum RaffleType {
    DEFAULT("default"),
    TWO_DIMENSIONAL("twoDimensional");

    private final String description;

    RaffleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
