package com.example.rafflebuddy.repository;

import com.example.rafflebuddy.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaffleRepository extends JpaRepository<Raffle, Long> { }
