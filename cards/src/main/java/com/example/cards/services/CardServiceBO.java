package com.example.cards.services;

import com.example.cards.dto.CardDTO;
import org.springframework.stereotype.Service;

public interface  CardServiceBO {
    void createCustomerCard(CardDTO cardDTO);
    CardDTO getCardDetails(String cardNumber);
}
