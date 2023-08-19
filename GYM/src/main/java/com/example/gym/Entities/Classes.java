package com.example.gym.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Data
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @Column(nullable = false)
    private String className;

    @Column
    private String description;

    @Column
    private float price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "coach_id") // Use @JoinColumn here
    private Users coach; // Assuming a coach is also a user

    @ManyToMany(mappedBy = "classes")

    private Set<Users> users;

}
