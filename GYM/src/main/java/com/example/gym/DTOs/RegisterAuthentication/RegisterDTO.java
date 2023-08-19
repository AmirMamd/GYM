package com.example.gym.DTOs.RegisterAuthentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String fullName;
    private String username;
    private String email;
    private String Password;
}
