package com.example.rafflebuddy.controller;

import com.example.rafflebuddy.model.RaffleSlot;
import com.example.rafflebuddy.service.RaffleSlotService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/slots/")
public class RaffleSlotController {

    final RaffleSlotService raffleSlotService;

    public RaffleSlotController(RaffleSlotService raffleSlotService) {
        this.raffleSlotService = raffleSlotService;
    }

    @GetMapping("{id}")
    public ResponseEntity<RaffleSlot> getSlot(@PathVariable long id) {
        return new ResponseEntity<>(raffleSlotService.getRaffleSlotById(id), HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<RaffleSlot> updateSlot(@PathVariable long id, @RequestBody RaffleSlot raffleSlot) {
        return new ResponseEntity<>(raffleSlotService.updateRaffleSlot(id, raffleSlot), HttpStatus.OK);
    }
}
