package com.microservice.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "Name cannot be null")
    @Size(min = 5,max = 30)
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should have proper format")
    private String email;
    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please provide correct mobile number")
    private String mobileNumber;
    private AccountsDTO accountsInfo;
}
