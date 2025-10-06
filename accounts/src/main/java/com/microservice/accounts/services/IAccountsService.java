package com.microservice.accounts.services;

import com.microservice.accounts.dto.CustomerDTO;

public interface IAccountsService {
    void createAccount(CustomerDTO customerDTO);
}
