package com.example.rafflebuddy.service.imp;

import com.example.rafflebuddy.model.RaffleImage;
import com.example.rafflebuddy.repository.RaffleImageRepository;
import com.example.rafflebuddy.service.RaffleImageService;
import com.example.rafflebuddy.service.RaffleService;
import org.springframework.stereotype.Service;

@Service
public class RaffleImageServiceImpl implements RaffleImageService {

    final RaffleImageRepository raffleImageRepository;

    public RaffleImageServiceImpl(RaffleImageRepository raffleImageRepository) {
        this.raffleImageRepository = raffleImageRepository;
    }

    public RaffleImage saveRaffleImage(RaffleImage raffleImage) {
        return raffleImageRepository.save(raffleImage);
    }
}
