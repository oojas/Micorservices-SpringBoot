package com.example.cards.repository;

import com.example.cards.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardServiceRepo extends JpaRepository<Cards,Long> {

}
