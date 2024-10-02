package com.example.rafflebuddy.enums;

public enum RaffleStatus {
    NOT_AVAILABLE("not availabe"),
    AVAILABLE("available");
    private final String description;
    RaffleStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
