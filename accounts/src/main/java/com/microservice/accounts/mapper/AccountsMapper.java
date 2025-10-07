package com.microservice.accounts.mapper;

import com.microservice.accounts.dto.AccountsDTO;
import com.microservice.accounts.entities.Accounts;

public class AccountsMapper {
    public static AccountsDTO accEntityToDTO(Accounts accounts,AccountsDTO accountsDTO){
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static void accDTOToAccountsEntity(Accounts accounts,AccountsDTO accountsDTO,boolean isInsert){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
    }
}
