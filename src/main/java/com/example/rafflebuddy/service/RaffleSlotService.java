package com.example.rafflebuddy.service;

import com.example.rafflebuddy.model.Raffle;
import com.example.rafflebuddy.model.RaffleSlot;

import java.util.List;

public interface RaffleSlotService {
    void createRaffleSlot(Raffle raffle, RaffleSlot raffleSlot);
    RaffleSlot updateRaffleSlot(long id, RaffleSlot raffleSlot);
    RaffleSlot getRaffleSlotById(long id);
    int getAvailableSlot(long id);
}
