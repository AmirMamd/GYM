package com.example.gym.Repositories;

import com.example.gym.Entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassesRepository extends JpaRepository<Classes,Long> {
}
