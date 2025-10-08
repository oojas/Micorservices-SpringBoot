package com.microservice.accounts.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {
    @Pattern(regexp = "(^$|[0-9]{10})")
    private Long accountNumber;
    @NotEmpty(message = "Account Type cannot be null")
    private String accountType;
    @NotEmpty(message = "Branch Address cannot be null")
    private String branchAddress;
}
