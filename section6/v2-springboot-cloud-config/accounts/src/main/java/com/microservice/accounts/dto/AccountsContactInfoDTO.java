package com.microservice.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
// This class is getting used because we can have changes at runtime for configurations and we need to reflect them without restarting the application
public class AccountsContactInfoDTO{
    private String message;
    private HashMap<String,String> contactDetails;
    private List<String> onCallSupport;
}

// This record class is used if we have configurations that cannot be changed, because in record classes you cannot change the value of variables.
//public record AccountsContactInfoDTO(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//}
