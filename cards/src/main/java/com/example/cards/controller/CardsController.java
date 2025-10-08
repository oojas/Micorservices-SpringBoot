package com.example.cards.controller;

import com.example.cards.constants.CardsConstants;
import com.example.cards.dto.CardDTO;
import com.example.cards.dto.ResponseDTO;
import com.example.cards.repository.CardServiceRepo;
import com.example.cards.services.CardServiceBO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cards/")
@AllArgsConstructor
public class CardsController {
    private CardServiceBO cardServiceBO;
    @PostMapping("/createCard")
    public ResponseEntity<ResponseDTO> createCard(
           @RequestBody CardDTO cardDTO){
        cardServiceBO.createCustomerCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }
}
