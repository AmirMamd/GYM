package com.example.gym.Repositories;

import com.example.gym.Entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface UsersRepository extends JpaRepository<Users,Long> {

    boolean existsByUsername(String username);
    Users findByUsernameOrEmail (String username,String email);
    Users findByEmail(String email);
    boolean existsByEmail(String email);
}
