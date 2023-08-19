package com.example.gym.Entities;
import com.example.gym.Enums.Role;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Use the Enum to define user roles

    @ManyToOne
    @JoinColumn(name = "subscription_offer_id")
    private Offers subscriptionOffer;     //One offer can be subscribed by Many Users.
                                        //if user is coach then subscriptionOffer is null

    @ManyToMany
    @JoinTable(
            name="users_classes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )                                    //Many Users can Subscribe many Classes
    private Set<Classes> classes;

}
