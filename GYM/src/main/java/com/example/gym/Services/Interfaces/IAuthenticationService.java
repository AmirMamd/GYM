package com.example.gym.Services.Interfaces;

import com.example.gym.DTOs.LoginAuthentication.LoginDTO;
import com.example.gym.DTOs.RegisterAuthentication.RegisterDTO;

public interface IAuthenticationService {
    String login(LoginDTO loginDto);


    String register(RegisterDTO registerDto);
}
