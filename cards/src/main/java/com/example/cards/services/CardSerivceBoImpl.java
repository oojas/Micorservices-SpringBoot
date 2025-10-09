package com.example.cards.services;

import com.example.cards.dto.CardDTO;
import com.example.cards.entities.Cards;
import com.example.cards.exceptions.ResourceNotFoundException;
import com.example.cards.mapper.CardsMapper;
import com.example.cards.repository.CardServiceRepo;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardSerivceBoImpl implements CardServiceBO{
    @Autowired
    private CardServiceRepo cardServiceRepo;
    @Override
    public void createCustomerCard(CardDTO cardDTO) {
        Long cardNumber=1000000000L+new Random().nextInt(90000000);
        Cards cards= CardsMapper.generateCard(cardDTO,cardNumber);
        cardServiceRepo.save(cards);
    }

    @Override
    public CardDTO getCardDetails(String cardNumber) {
       Cards cardDetails=cardServiceRepo.findByCardNumber(cardNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card","Card Number",cardNumber)
        );
       return  CardsMapper.mapToCardsDTO(cardDetails);
    }
}
