package com.microservices.loans.services;

import com.microservices.loans.DTO.LoanDTO;
import com.microservices.loans.DTO.ResponseDTO;
import com.microservices.loans.constants.LoanConstants;
import com.microservices.loans.entities.Loans;
import com.microservices.loans.exceptions.LoanAccountAlreadyExists;
import com.microservices.loans.exceptions.ResourceNotFound;
import com.microservices.loans.mapper.LoanMapper;
import com.microservices.loans.repository.LoanRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanServiceBO{
    private LoanRepository loanRepository;
    @Override
    public ResponseEntity<ResponseDTO> createLoan(LoanDTO loanDTO) {
        Optional<Loans> loansOptional=loanRepository.findByMobileNumber(loanDTO.getMobileNumber());
        if(loansOptional.isPresent()){
            throw new LoanAccountAlreadyExists("Loan with this Mobile Number already exists");
        }
        Loans loans= LoanMapper.generateLoanAccount(loanDTO);
        loanRepository.save(loans);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<LoanDTO> fetchLoanByMobileNumber(String mobileNumber) {
        Optional<Loans> loan= loanRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()){
            return new ResponseEntity<>(LoanMapper.mapToLoanDTO(loan.get()),HttpStatus.OK);
        }
        throw new ResourceNotFound("Loan Account","Mobile Number",mobileNumber);
    }

    @Override
    public ResponseEntity<LoanDTO> fetchLoanByLoanNumber(String loanNumber) {
        Optional<Loans> loan= loanRepository.findByLoanNumber(loanNumber);
        if(loan.isPresent()){
            return new ResponseEntity<>(LoanMapper.mapToLoanDTO(loan.get()),HttpStatus.OK);
        }
        throw new ResourceNotFound("Loan Account","Loan Number",loanNumber);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateLoanDetails(LoanDTO loanDTO) {
        Optional<Loans> loansOptional=loanRepository.findByMobileNumber(loanDTO.getMobileNumber());
        if(loansOptional.isPresent()){
            loanRepository.save(LoanMapper.mapToLoan(loanDTO,loansOptional.get()));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200)
            );
        }
        throw new ResourceNotFound("Loan Account","Mobile Number", loanDTO.getMobileNumber());
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteLoanDetails(String mobileNumber) {
        Optional<Loans> loansOptional=loanRepository.findByMobileNumber(mobileNumber);
        if(loansOptional.isPresent()){
            loanRepository.deleteById(loansOptional.get().getLoanId());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LoanConstants.STATUS_200,LoanConstants.DELETE_SUCCESS));
        }
        throw new ResourceNotFound("Loan Account","Mobile Number",mobileNumber);
    }
}
