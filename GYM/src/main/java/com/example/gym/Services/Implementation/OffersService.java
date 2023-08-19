package com.example.gym.Services.Implementation;

import com.example.gym.Repositories.OffersRepository;
import com.example.gym.Services.Interfaces.IOffersService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OffersService implements IOffersService {
    private final OffersRepository offersRepository;
}
