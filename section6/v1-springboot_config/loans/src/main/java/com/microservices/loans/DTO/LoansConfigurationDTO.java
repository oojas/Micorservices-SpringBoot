package com.microservices.loans.DTO;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;

@ConfigurationProperties(prefix = "loans")
public record LoansConfigurationDTO(String message, HashMap<String,String> contactDetails, List<String> onCallSupport) {
}
