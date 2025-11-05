package com.microservices.loans.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s not found with input field %s and input data : %s",resourceName,fieldName,fieldValue));
    }
}
