package com.microservices.loans.services;

import com.microservices.loans.DTO.LoanDTO;
import com.microservices.loans.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoanServiceBO {
    ResponseEntity<ResponseDTO> createLoan(LoanDTO loanDTO);
    ResponseEntity<LoanDTO> fetchLoanByMobileNumber(String mobileNumber);
    ResponseEntity<LoanDTO> fetchLoanByLoanNumber(String loanNumber);
    ResponseEntity<ResponseDTO> updateLoanDetails(LoanDTO loanDTO);
    ResponseEntity<ResponseDTO> deleteLoanDetails(String mobileNumber);
}
