package com.microservice.accounts.exceptions;

import com.microservice.accounts.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExists extends RuntimeException{
    public CustomerAlreadyExists(String message){
        super(message);
    }
}
