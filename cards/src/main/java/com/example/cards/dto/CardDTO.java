package com.example.cards.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CardDTO {
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;
}
