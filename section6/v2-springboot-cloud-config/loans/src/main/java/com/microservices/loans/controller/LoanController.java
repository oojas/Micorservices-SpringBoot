package com.microservices.loans.controller;

import com.microservices.loans.DTO.LoanDTO;
import com.microservices.loans.DTO.LoansConfigurationDTO;
import com.microservices.loans.DTO.ResponseDTO;
import com.microservices.loans.services.LoanServiceBO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class LoanController {
    @Autowired
    LoanServiceBO loanServiceBO;
    @Autowired
    private LoansConfigurationDTO loansConifugrationDTO;
    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDTO> createLoanAccount(@Valid @RequestBody LoanDTO loanDTO){
        return loanServiceBO.createLoan(loanDTO);
    }

    @GetMapping(path = "/fetch",params = "mobileNumber")
    ResponseEntity<LoanDTO> findByMobileNumber(
            @Valid
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a 10 digit valid Mobile Number")
            @RequestParam String mobileNumber){
        return loanServiceBO.fetchLoanByMobileNumber(mobileNumber);
    }

    @GetMapping(path="/fetch",params = "loanNumber")
    ResponseEntity<LoanDTO> findByLoanNumber(
            @Valid
            @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a 10 digit valid Loan Number")
            @RequestParam String loanNumber){
        return loanServiceBO.fetchLoanByLoanNumber(loanNumber);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loanDTO){
        return loanServiceBO.updateLoanDetails(loanDTO);
    }
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDTO> deleteLoanDetails(@Valid @RequestParam String mobileNumber){
        return loanServiceBO.deleteLoanDetails(mobileNumber);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansConfigurationDTO> getContactInfo(){
        return new ResponseEntity<>(loansConifugrationDTO, HttpStatus.OK);
    }
}
