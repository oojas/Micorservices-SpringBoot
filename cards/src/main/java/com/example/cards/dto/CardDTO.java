package com.example.cards.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CardDTO {
    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a valid 10 digit mobile Number")
    private String mobileNumber;
    private String cardNumber;
    @NotEmpty(message = "Card Type cannot be empty")
    private String cardType;
    @NotNull(message = "Total Limit cannot be empty")
    private Long totalLimit;
    @NotNull(message = "Amount Used cannot be empty")
    private Long amountUsed;
    @NotNull(message = "Available Amount cannot be empty")
    private Long availableAmount;
}
