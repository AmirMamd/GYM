package com.example.gym.Mappers;

import com.example.gym.DTOs.UsersDTO;
import com.example.gym.Entities.Users;
import com.example.gym.Enums.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.Locale.filter;

@Service
public class UserDTOMapper implements Function<Users, UsersDTO> {

    @Override
    public UsersDTO apply(Users users) {
        return new UsersDTO(
                users.getId(),
                users.getUsername(),
                users.getFullName(),
                users.getDateOfBirth(),
                users.getEmail(),
                users.getRole()
        );
    }
}
