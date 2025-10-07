package com.microservice.accounts.exceptions;

import com.microservice.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice // This annotation basically tells the springboot to handle exceptions throughout the package. Basically if we have created
// a custom exception, then that exception would need to be caught by every controller where we invoke it so in order to not duplicate the exception handeling
// logic, we write a global exception handler

public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExists.class)
    // This annotation will tell the framework that this method will handle CustomerAlreadyExists exception
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExists(CustomerAlreadyExists exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFound(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }


}
