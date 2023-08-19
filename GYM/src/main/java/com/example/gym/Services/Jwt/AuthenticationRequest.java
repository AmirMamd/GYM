package com.example.gym.Services.Jwt;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
