package com.example.cards.mapper;

import com.example.cards.dto.CardDTO;
import com.example.cards.entities.Cards;
import lombok.Data;

@Data
public class CardsMapper {
    public static Cards mapToCardsDTO(CardDTO cardDTO,Long cardNumber){
        Cards cards=new Cards();
        cards.setCardNumber(cardDTO.getCardNumber());
        cards.setCardType(cardDTO.getCardType());
        cards.setAmountUsed(cardDTO.getAmountUsed());
        cards.setMobileNumber(cardDTO.getMobileNumber());
        cards.setTotalLimit(cardDTO.getTotalLimit());
        cards.setAvailableAmount(cardDTO.getAvailableAmount());
        return cards;
    }
    public static CardDTO mapToCards(Cards cards){
        CardDTO cardDTO=new CardDTO();
        cardDTO.setCardNumber(cards.getCardNumber());
        cardDTO.setCardType(cards.getCardType());
        cardDTO.setAmountUsed(cards.getAmountUsed());
        cardDTO.setMobileNumber(cards.getMobileNumber());
        cardDTO.setTotalLimit(cards.getTotalLimit());
        cardDTO.setAvailableAmount(cards.getAvailableAmount());
        return cardDTO;
    }

    public static Cards generateCard(CardDTO cardDTO,Long cardNumber){
        Cards cards=new Cards();
        cards.setCardNumber(String.valueOf(cardNumber));
        cards.setCardType(cardDTO.getCardType());
        cards.setAmountUsed(cardDTO.getAmountUsed());
        cards.setMobileNumber(cardDTO.getMobileNumber());
        cards.setTotalLimit(cardDTO.getTotalLimit());
        cards.setAvailableAmount(cardDTO.getAvailableAmount());
        return cards;
    }
}
