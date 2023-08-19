package com.example.gym.Services.Implementation;

import com.example.gym.DTOs.UsersDTO;
import com.example.gym.Entities.Users;
import com.example.gym.Enums.Role;
import com.example.gym.Mappers.UserDTOMapper;
import com.example.gym.Repositories.UsersRepository;
import com.example.gym.Services.Interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements IUserService {
    private final UsersRepository usersRepository;
    private final UserDTOMapper userDTOMapper;

    @Override
    public List<UsersDTO> getUsers(){
        return  usersRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .filter(x -> x.getRole()==Role.USER)
                .collect(Collectors.toList());
    }
    @Override
    public List<UsersDTO> getCoaches(){
        return  usersRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .filter(x -> x.getRole()==Role.COACH)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersDTO> getManagers(){
        return  usersRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .filter(x -> x.getRole()== Role.MANAGER)
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(Users user) {
        usersRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public boolean validateManager(Long id) {
        Users user = usersRepository.findById(id).get();
        return user.getRole() == Role.MANAGER;
    }

}
