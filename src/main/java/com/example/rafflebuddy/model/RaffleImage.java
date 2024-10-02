package com.example.rafflebuddy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="raffle_images")
@ToString(exclude = "raffle")

public class RaffleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String src;
    private String description;

    @ManyToOne
    @JoinColumn(name="raffle_id", nullable = false)
    @JsonBackReference
    private Raffle raffle;
}
