package com.microservices.loans.exceptions;


public class LoanAccountAlreadyExists extends RuntimeException{
    public LoanAccountAlreadyExists(String message){
        super(message);
    }
}
