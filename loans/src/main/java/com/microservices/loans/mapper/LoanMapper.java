package com.microservices.loans.mapper;

import com.microservices.loans.DTO.LoanDTO;
import com.microservices.loans.entities.Loans;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class LoanMapper {
    public static Loans generateLoanAccount(LoanDTO loanDTO){
        Random random = new Random();
        long loanNumber = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        Loans loans=new Loans();
        loans.setLoanNumber(Long.toString(loanNumber));
        loans.setLoanType(loanDTO.getLoanType());
        loans.setTotalLoan(loanDTO.getTotalLoan());
        loans.setAmountPaid(loanDTO.getAmountPaid());
        loans.setMobileNumber(loanDTO.getMobileNumber());
        loans.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loans;
    }
    public static LoanDTO mapToLoanDTO(Loans loan){
        LoanDTO loanDTO=new LoanDTO();
        loanDTO.setLoanNumber(loan.getLoanNumber());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setTotalLoan(loan.getTotalLoan());
        loanDTO.setAmountPaid(loan.getAmountPaid());
        loanDTO.setMobileNumber(loan.getMobileNumber());
        loanDTO.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDTO;
    }
    public static Loans mapToLoan(LoanDTO loanDTO, Loans loan){
        loan.setLoanNumber(loanDTO.getLoanNumber());
        loan.setLoanType(loanDTO.getLoanType());
        loan.setTotalLoan(loanDTO.getTotalLoan());
        loan.setAmountPaid(loanDTO.getAmountPaid());
        loan.setMobileNumber(loanDTO.getMobileNumber());
        loan.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loan;
    }
}
