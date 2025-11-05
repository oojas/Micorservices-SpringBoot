package com.microservice.accounts.controller;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dto.AccountsContactInfoDTO;
import com.microservice.accounts.dto.AccountsDTO;
import com.microservice.accounts.dto.CustomerDTO;
import com.microservice.accounts.dto.ResponseDTO;
import com.microservice.accounts.services.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts/")
@Validated
public class AccountController {
  @Autowired
  private IAccountsService iAccountsService;
  @Value("${build.version}")
  private String buildVersion;
  @Autowired
  private Environment environment;
  @Autowired
  private AccountsContactInfoDTO accountsContactInfoDTO;
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/getAccountDetails")
    public ResponseEntity<CustomerDTO> getAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Please provide correct mobile number")
            @RequestParam String mobileNumber){
        CustomerDTO customerDTO=iAccountsService.fetchAccountDetails(mobileNumber);
        return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCustomerDetails(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated=iAccountsService.updateCustomer(customerDTO);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCustomerDetails(
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Please provide correct Account Number")
            @RequestParam String accountNumber){
        iAccountsService.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.DELETE_SUCCESS));
    }

    @GetMapping("/getBuildInfo")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDTO> getContactInfo(){
        return new ResponseEntity<AccountsContactInfoDTO>(accountsContactInfoDTO,HttpStatus.OK);
    }
}
