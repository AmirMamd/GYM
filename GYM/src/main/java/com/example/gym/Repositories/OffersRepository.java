package com.example.gym.Repositories;

import com.example.gym.Entities.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offers,Long> {
}
