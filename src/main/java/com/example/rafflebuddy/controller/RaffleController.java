package com.example.rafflebuddy.controller;

import com.example.rafflebuddy.dto.RaffleRequestBody;
import com.example.rafflebuddy.dto.RaffleResponse;
import com.example.rafflebuddy.dto.ResponseDTO;
import com.example.rafflebuddy.model.Raffle;
import com.example.rafflebuddy.model.User;
import com.example.rafflebuddy.service.RaffleImageService;
import com.example.rafflebuddy.service.RaffleService;
import com.example.rafflebuddy.service.RaffleSlotService;
import com.example.rafflebuddy.utils.MoneyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/raffle")
public class RaffleController {

    final RaffleService raffleService;
    final RaffleSlotService raffleSlotService;

    public RaffleController(RaffleService raffleService, RaffleSlotService raffleSlotService) {
        this.raffleService = raffleService;
        this.raffleSlotService = raffleSlotService;
    }

    @PostMapping()
    public ResponseEntity<Raffle> addRaffle (@RequestBody Raffle raffle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        raffle.setUser(currentUser);
        return new ResponseEntity<Raffle>(raffleService.addRaffle(raffle), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<RaffleResponse>>> getRaffles() {
        List<Raffle> raffleList = raffleService.getRaffles();
        List<RaffleResponse> raffleResponses = new ArrayList<>();

        for(Raffle raffle: raffleList) {
            raffle.setAvailableSlot(raffleSlotService.getAvailableSlot(raffle.getId()));
            RaffleResponse raffleResponse = RaffleResponse.builder()
                    .id(raffle.getId())
                    .slotCount(raffle.getSlotCount())
                    .availableSlot(raffle.getSlotCount())
                    .slotAmount(MoneyUtils.moneyFormat(raffle.getSlotAmount()))
                    .title(raffle.getTitle())
                    .raffleDate(raffle.getRaffleDate())
                    .raffleInstructions(raffle.getRaffleInstructions())
                    .slotType(raffle.getSlotType())
                    .rafflePrize(raffle.getRafflePrize())
                    .raffleImages(raffle.getRaffleImages())
                    .raffleSlots(raffle.getRaffleSlots())
                    .build();
            raffleResponses.add(raffleResponse);
        }
        ResponseDTO<List<RaffleResponse>> response = ResponseDTO.<List<RaffleResponse>>builder()
                .data(raffleResponses)
                .success(true)
                .message("Get raffles success")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> deleteRaffle(@PathVariable long id) {
        raffleService.deleteRaffle(id);
       return new ResponseEntity<String>("Deleted Successfully!", HttpStatus.OK);
    }
}
