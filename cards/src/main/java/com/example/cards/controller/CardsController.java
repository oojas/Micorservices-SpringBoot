package com.example.cards.controller;

import com.example.cards.constants.CardsConstants;
import com.example.cards.dto.CardDTO;
import com.example.cards.dto.ResponseDTO;
import com.example.cards.services.CardServiceBO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cards/")
@AllArgsConstructor
@Validated
public class CardsController {
    private CardServiceBO cardServiceBO;
    @PostMapping("/createCard")
    public ResponseEntity<ResponseDTO> createCard(
           @RequestBody @Valid CardDTO cardDTO){
        cardServiceBO.createCustomerCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @GetMapping(value = "/fetch",params = "cardNumber")
    public ResponseEntity<CardDTO> getCardDetails(@RequestParam
        @Valid
        @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a 10 digit valid card Number") String cardNumber){
        CardDTO response=cardServiceBO.getCardDetails(cardNumber);
        return new ResponseEntity<CardDTO>(response,HttpStatus.OK);
    }
    @GetMapping(value = "/fetch",params = "mobileNumber")
    public ResponseEntity<CardDTO> getCardDetailsFromMobileNumber(@RequestParam
           @Valid @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid 10 digit Mobile Number") String mobileNumber){
        CardDTO response=cardServiceBO.getCardDetailsFromMobileNumber(mobileNumber);
        return new ResponseEntity<CardDTO>(response,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@RequestBody @Valid CardDTO cardDTO){
        boolean isUpdated=cardServiceBO.updateCardDetails(cardDTO);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(CardsConstants.STATUS_500,CardsConstants.MESSAGE_500));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(@RequestParam
      @Valid @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid 10 digit Mobile Number")String mobileNumber){
        cardServiceBO.deleteCardDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.DELETE_SUCCESS));
    }
}
