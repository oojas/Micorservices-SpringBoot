package com.microservice.accounts.services.impl;

import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.repository.AccountsRepository;
import com.microservice.accounts.repository.CustomerRepository;
import com.microservice.accounts.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {

    }
}
