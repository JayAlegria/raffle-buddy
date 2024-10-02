package com.example.rafflebuddy.service;

import com.example.rafflebuddy.dto.RaffleRequestBody;
import com.example.rafflebuddy.model.Raffle;

import java.util.List;

public interface RaffleService {
    Raffle addRaffle(Raffle raffle);
    List<Raffle> getRaffles();
    void deleteRaffle(long id);
    Raffle getRaffleById(long id);
}
