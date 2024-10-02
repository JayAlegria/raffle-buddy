package com.example.rafflebuddy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="raffle_slots")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RaffleSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "paid")
    private boolean isSlotPaid;

    @Column(name = "status")
    private String status = "available";

    @Column(name = "slot_title")
    private String slotTitle;

    @ManyToOne
    @JoinColumn(name = "raffle_id", nullable = false)
    @JsonBackReference
    private Raffle raffle;
}
