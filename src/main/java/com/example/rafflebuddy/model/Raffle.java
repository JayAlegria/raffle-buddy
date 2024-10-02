package com.example.rafflebuddy.model;

import com.example.rafflebuddy.enums.RaffleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name="raffle_items")
@ToString(exclude = {"raffleImages", "raffleSlots"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="raffle_date", nullable = false)
    private String raffleDate;

    @Column(name="raffle_instructions", nullable = false)
    private String raffleInstructions;

    @Column(name="slot_type", nullable = false)
    private String slotType = RaffleType.DEFAULT.getDescription();

    @Column(name="slot_count")
    private int slotCount;

    @Column(name="slot_amount")
    private String slotAmount;

    @Embedded
    private RafflePrize rafflePrize;

    @OneToMany(mappedBy = "raffle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RaffleImage> raffleImages;

    @OneToMany(mappedBy = "raffle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RaffleSlot> raffleSlots;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Transient
    private int first2dDigit;
    @Transient
    private int second2dDigit;

    @Transient
    private int availableSlot;
}
