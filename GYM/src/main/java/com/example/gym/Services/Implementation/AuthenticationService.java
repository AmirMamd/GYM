package com.example.gym.Services.Implementation;

import com.example.gym.Entities.Users;
import com.example.gym.DTOs.RegisterAuthentication.RegisterDTO;
import com.example.gym.DTOs.LoginAuthentication.LoginDTO;
import com.example.gym.Repositories.UsersRepository;
import com.example.gym.Services.Interfaces.IAuthenticationService;
import com.example.gym.Services.Jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService implements IAuthenticationService { private AuthenticationManager authenticationManager;
    private UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                          UsersRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public String login(LoginDTO loginDto) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername() , loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Users user = userRepository.findByEmail(loginDto.getUsername());
        String token = jwtUtil.generateToken(user);
        return token;
    }

    @Override
    public String register(RegisterDTO registerDto) {
        Users user = new Users();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        LoginDTO loginDto = new LoginDTO();
        loginDto.setPassword(registerDto.getPassword());
        loginDto.setUsername(registerDto.getUsername());
        return login(loginDto);
    }
}
