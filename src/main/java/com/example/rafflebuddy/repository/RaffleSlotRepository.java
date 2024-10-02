package com.example.rafflebuddy.repository;

import com.example.rafflebuddy.model.RaffleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RaffleSlotRepository extends JpaRepository<RaffleSlot, Long> {
    @Query("SELECT COUNT(s) FROM RaffleSlot s WHERE s.status = 'available' AND s.raffle.id = :raffleId")
    int countAvailableSlots(@Param("raffleId") long id);
}
