package com.example.gym.Services.Interfaces;

import com.example.gym.DTOs.UsersDTO;
import com.example.gym.Entities.Users;

import java.util.List;

public interface IUserService {

    List<UsersDTO> getUsers();

    List<UsersDTO> getCoaches();

    List<UsersDTO> getManagers();

    void saveUser(Users user);

    Object getUserById(Long id);

    Users updateUser(Users user);

    void deleteUserById(Long id);

    boolean validateManager(Long id);
}
