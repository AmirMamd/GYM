package com.example.gym.Controllers;

import com.example.gym.DTOs.RegisterAuthentication.RegisterDTO;
import com.example.gym.DTOs.LoginAuthentication.LoginDTO;
import com.example.gym.Services.Interfaces.IAuthenticationService;
import com.example.gym.Services.Jwt.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    IAuthenticationService authService;

    public AuthenticationController(IAuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto);
        AuthenticationResponse jwrAuthnResponse= new AuthenticationResponse();
        jwrAuthnResponse.setAccessToken(token);
        return ResponseEntity.ok(jwrAuthnResponse);
    }
    @PostMapping(value = {"signup" , "register"})
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDTO registerDto){
        String token = authService.register(registerDto);
        AuthenticationResponse  jwtAuthnResponse= new AuthenticationResponse();
        jwtAuthnResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthnResponse, HttpStatus.CREATED);
    }
}