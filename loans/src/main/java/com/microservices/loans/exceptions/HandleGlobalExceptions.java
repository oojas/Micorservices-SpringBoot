package com.microservices.loans.exceptions;

import com.microservices.loans.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandleGlobalExceptions extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LoanAccountAlreadyExists.class)
    public ResponseEntity<ErrorResponseDTO> handleLoanAlreadyExistsException(WebRequest webRequest,LoanAccountAlreadyExists ex){
        ErrorResponseDTO responseDTO=new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFound res,WebRequest webRequest){
        ErrorResponseDTO error=new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                res.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
