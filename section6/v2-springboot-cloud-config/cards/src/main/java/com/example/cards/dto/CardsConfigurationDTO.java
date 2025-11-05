package com.example.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "cards")
public record CardsConfigurationDTO(String message, HashMap<String,String> contactDetails, List<String> onCallSupport) {
}
