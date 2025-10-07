package com.microservice.accounts.services;
import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.exceptions.ResourceNotFoundException;

public interface IAccountsService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccountDetails(String mobileNumber) throws ResourceNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO);
    void deleteAccount(String accountNumber);
}
