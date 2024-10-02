package com.example.rafflebuddy.dto;

import com.example.rafflebuddy.enums.RaffleType;
import com.example.rafflebuddy.model.RaffleImage;
import com.example.rafflebuddy.model.RafflePrize;
import com.example.rafflebuddy.model.RaffleSlot;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RaffleResponse {
    private long id;
    private int slotCount;
    private int availableSlot;
    private String slotAmount;
    private String title;
    private String raffleDate;
    private String raffleInstructions;
    private String slotType;
    private RafflePrize rafflePrize;
    private List<RaffleImage> raffleImages;
    private List<RaffleSlot> raffleSlots;

}
