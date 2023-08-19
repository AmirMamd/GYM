package com.example.gym.DTOs;

import com.example.gym.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class UsersDTO {
    private Long id;
    private String username;
    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private Role role;

}
