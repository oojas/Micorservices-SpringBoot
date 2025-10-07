package com.microservice.accounts.controller;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsDTO;
import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.dto.ResponseDTO;
import com.microservice.accounts.services.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts/")
public class AccountController {
  @Autowired
  private IAccountsService iAccountsService;
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> getAccountDetails(@RequestParam String mobileNumber){
        CustomerDTO customerDTO=iAccountsService.fetchAccountDetails(mobileNumber);
        return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCustomerDetails(@RequestBody CustomerDTO customerDTO){
        boolean isUpdated=iAccountsService.updateCustomer(customerDTO);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCustomerDetails(@RequestParam String accountNumber){
        iAccountsService.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.DELETE_SUCCESS));
    }
}
