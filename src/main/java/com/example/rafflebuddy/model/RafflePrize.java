package com.example.rafflebuddy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RafflePrize {

    @Column(name = "Prizes")
    private String jackpot;

    @ElementCollection
    private List<Consolation> consolations;
}
