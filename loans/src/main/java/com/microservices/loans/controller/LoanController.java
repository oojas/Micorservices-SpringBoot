package com.microservices.loans.controller;

import com.microservices.loans.DTO.LoanDTO;
import com.microservices.loans.DTO.ResponseDTO;
import com.microservices.loans.services.LoanServiceBO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoanController {
    LoanServiceBO loanServiceBO;
    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDTO> createLoanAccount(@RequestBody LoanDTO loanDTO){
        return loanServiceBO.createLoan(loanDTO);
    }

    @GetMapping(path = "/fetch",params = "mobileNumber")
    ResponseEntity<LoanDTO> findByMobileNumber(@RequestParam String mobileNumber){
        return loanServiceBO.fetchLoanByMobileNumber(mobileNumber);
    }

    @GetMapping(path="/fetch",params = "loanNumber")
    ResponseEntity<LoanDTO> findByLoanNumber(@RequestParam String loanNumber){
        return loanServiceBO.fetchLoanByLoanNumber(loanNumber);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseDTO> updateLoanDetails(@RequestBody LoanDTO loanDTO){
        return loanServiceBO.updateLoanDetails(loanDTO);
    }
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam String mobileNumber){
        return loanServiceBO.deleteLoanDetails(mobileNumber);
    }
}
