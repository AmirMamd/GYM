package com.example.gym.Services.Implementation;

import com.example.gym.Repositories.ClassesRepository;
import com.example.gym.Services.Interfaces.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassesService implements IClassesService {

    private final ClassesRepository classesRepository;
}
