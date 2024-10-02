package com.example.rafflebuddy.dto;

import com.example.rafflebuddy.model.RaffleImage;
import com.example.rafflebuddy.model.RafflePrize;
import lombok.Data;

import java.util.List;

@Data
public class RaffleRequestBody {
    private String title;
    private String raffleDate;
    private String raffleInstructions;
    private int slotCount;
    private RafflePrize rafflePrize;
    private List<RaffleImage> raffleImages;
}
