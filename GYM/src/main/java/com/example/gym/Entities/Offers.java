package com.example.gym.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table
@Data
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @Column(nullable = false)
    private String offerName;

    @Column(nullable = false)
    private String promoCode;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

}
