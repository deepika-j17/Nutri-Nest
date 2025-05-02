package com.example.Nutri_Nest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class GoalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;
    private Double totalCaloriesConsumed;
    private Double totalCaloriesBurned;
    private Double weightChange;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
