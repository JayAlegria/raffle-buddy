package com.example.rafflebuddy.service.imp;

import com.example.rafflebuddy.enums.RaffleStatus;
import com.example.rafflebuddy.exception.SlotNotFoundException;
import com.example.rafflebuddy.model.Raffle;
import com.example.rafflebuddy.model.RaffleSlot;
import com.example.rafflebuddy.repository.RaffleSlotRepository;
import com.example.rafflebuddy.service.RaffleSlotService;
import org.springframework.stereotype.Service;


@Service
public class RaffleSlotServiceImpl implements RaffleSlotService {
    final RaffleSlotRepository raffleSlotRepository;

    public RaffleSlotServiceImpl(RaffleSlotRepository raffleSlotRepository) {
        this.raffleSlotRepository = raffleSlotRepository;
    }

    @Override
    public void createRaffleSlot(Raffle raffle, RaffleSlot raffleSlot) {
        for(int i = 0; i < raffle.getSlotCount(); i++) {
            RaffleSlot r = new RaffleSlot();
            r.setRaffle(raffle);
            raffleSlotRepository.save(raffleSlot);
        }
    }

    @Override
    public RaffleSlot updateRaffleSlot(long id, RaffleSlot raffleSlot) {
        RaffleSlot existingRaffleSlot = raffleSlotRepository.findById(id).orElseThrow(
                () -> new SlotNotFoundException("Slot not found")
        );

        if(!raffleSlot.getOwner().isEmpty()) {
            existingRaffleSlot.setStatus(RaffleStatus.NOT_AVAILABLE.getDescription());
        } else {
            existingRaffleSlot.setStatus(RaffleStatus.AVAILABLE.getDescription());
        }
        existingRaffleSlot.setOwner(raffleSlot.getOwner());
        existingRaffleSlot.setSlotPaid(raffleSlot.isSlotPaid());

        return raffleSlotRepository.save(existingRaffleSlot);
    }

    @Override
    public int getAvailableSlot(long id) {
        return raffleSlotRepository.countAvailableSlots(id);
    }

    @Override
    public RaffleSlot getRaffleSlotById(long id) {
        return raffleSlotRepository.findById(id).orElseThrow();
    }
}
