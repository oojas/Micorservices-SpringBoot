package com.microservice.accounts.controller;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts/")
public class AccountController {
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
}
