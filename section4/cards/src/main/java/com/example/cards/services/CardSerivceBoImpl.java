package com.example.cards.services;

import com.example.cards.dto.CardDTO;
import com.example.cards.entities.Cards;
import com.example.cards.exceptions.CardAlreadyPresent;
import com.example.cards.exceptions.ResourceNotFoundException;
import com.example.cards.mapper.CardsMapper;
import com.example.cards.repository.CardServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardSerivceBoImpl implements CardServiceBO{
    @Autowired
    private CardServiceRepo cardServiceRepo;

    @Override
    public void createCustomerCard(CardDTO cardDTO) throws CardAlreadyPresent{
        Optional<Cards> cardsOptional=cardServiceRepo.findByMobileNumber(cardDTO.getMobileNumber());
        if(cardsOptional.isPresent()){
            throw new CardAlreadyPresent("Card with this mobile Number already exists");
        }
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

    @Override
    public CardDTO getCardDetailsFromMobileNumber(String mobileNumber) {
        Cards cardDetails=cardServiceRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card","Mobile Number",mobileNumber)
        );
        return CardsMapper.mapToCardsDTO(cardDetails);
    }

    @Override
    public boolean updateCardDetails(CardDTO cardDTO) throws ResourceNotFoundException{
        Optional<Cards> cardsOptional=cardServiceRepo.findByMobileNumber(cardDTO.getMobileNumber());
        if(cardsOptional.isPresent()){
            Cards cardDetails=cardsOptional.get();
            CardsMapper.mapToCards(cardDetails,cardDTO);
            cardServiceRepo.save(cardDetails);
            return true;
        }else{
            throw new ResourceNotFoundException("Card","Mobile Number",cardDTO.getMobileNumber());
        }
    }

    @Override
    public void deleteCardDetails(String mobileNumber) {
        Optional<Cards> cardsOptional=cardServiceRepo.findByMobileNumber(mobileNumber);
        if(cardsOptional.isPresent()){
            Cards cardDetails=cardsOptional.get();
            cardServiceRepo.deleteById(cardDetails.getCardId());
        }else{
            throw new ResourceNotFoundException("Card","Mobile Number", mobileNumber);
        }
    }

}
