package com.example.rafflebuddy.service.imp;

import com.example.rafflebuddy.enums.RaffleType;
import com.example.rafflebuddy.exception.SlotNotFoundException;
import com.example.rafflebuddy.model.Raffle;
import com.example.rafflebuddy.model.RaffleSlot;
import com.example.rafflebuddy.repository.RaffleRepository;
import com.example.rafflebuddy.service.RaffleService;
import com.example.rafflebuddy.service.RaffleSlotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaffleServiceImpl implements RaffleService {
    final RaffleRepository raffleRepository;
    final RaffleSlotService raffleSlotService;

    public RaffleServiceImpl(RaffleRepository raffleRepository, RaffleSlotService raffleSlotService) {
        this.raffleRepository = raffleRepository;
        this.raffleSlotService = raffleSlotService;
    }

    @Override
    public List<Raffle> getRaffles() {
        return raffleRepository.findAll();
    }

    @Override
    public Raffle getRaffleById(long id) {
        return raffleRepository.findById(id).orElseThrow(
                () -> new SlotNotFoundException("Raffle not found")
        );
    }

    @Override
    public Raffle addRaffle(Raffle raffle) {

        Raffle savedRaffle = raffleRepository.save(raffle);

        List<RaffleSlot> raffleSlotList = new ArrayList<RaffleSlot>();
        if(savedRaffle.getSlotType().equalsIgnoreCase(RaffleType.TWO_DIMENSIONAL.getDescription())) {
            savedRaffle.setSlotCount(raffle.getFirst2dDigit() * raffle.getFirst2dDigit());
            for(int i = 0; i < raffle.getFirst2dDigit(); i++) {
                int multiplicand = i + 1;
                for(int j = 0; j < raffle.getSecond2dDigit(); j++) {
                    RaffleSlot raffleSlot = new RaffleSlot();
                    raffleSlot.setRaffle(savedRaffle);
                    raffleSlot.setSlotTitle(multiplicand + "*" + (j + 1));
                    raffleSlotService.createRaffleSlot(savedRaffle, raffleSlot);
                    raffleSlotList.add(raffleSlot);
                }
            }

        } else {
            for (int i = 0; i < savedRaffle.getSlotCount(); i++) {
                RaffleSlot raffleSlot = new RaffleSlot();
                raffleSlot.setRaffle(savedRaffle);
                raffleSlot.setSlotTitle(String.valueOf(i + 1));
                raffleSlotService.createRaffleSlot(savedRaffle, raffleSlot);
                raffleSlotList.add(raffleSlot);
            }
        }

        savedRaffle.setRaffleSlots(raffleSlotList);
        return raffleRepository.save(savedRaffle);
    }

    @Override
    public void deleteRaffle(long id) {
        Raffle selectedRaffle = raffleRepository.findById(id).orElseThrow(
                ()->new SlotNotFoundException("Raffle not exists")
        );
        raffleRepository.delete(selectedRaffle);
    }
}
