package com.example.rafflebuddy.repository;

import com.example.rafflebuddy.model.RaffleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaffleImageRepository extends JpaRepository<RaffleImage, Long> {
}
