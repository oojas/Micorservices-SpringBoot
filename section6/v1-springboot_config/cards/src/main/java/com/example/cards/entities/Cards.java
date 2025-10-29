package com.example.cards.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cards extends BaseEntity{
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column
    private String mobileNumber;
    @Column
    private String cardNumber;
    @Column
    private String cardType;
    @Column
    private Long totalLimit;
    @Column
    private Long amountUsed;
    @Column
    private Long availableAmount;
}

/**
 *   `card_id` int NOT NULL AUTO_INCREMENT,
 *   `mobile_number` varchar(15) NOT NULL,
 *   `card_number` varchar(100) NOT NULL,
 *   `card_type` varchar(100) NOT NULL,
 *   `total_limit` int NOT NULL,
 *   `amount_used` int NOT NULL,
 *   `available_amount` int NOT NULL,
 *   `created_at` date NOT NULL,
 *   `created_by` varchar(20) NOT NULL,
 *   `updated_at` date DEFAULT NULL,
 *   `updated_by` varchar(20) DEFAULT NULL,
 *   PRIMARY KEY (`card_id`)
 * */