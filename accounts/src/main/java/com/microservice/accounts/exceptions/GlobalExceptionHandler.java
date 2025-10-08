package com.microservice.accounts.exceptions;

import com.microservice.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice // This annotation basically tells the springboot to handle exceptions throughout the package. Basically if we have created
// a custom exception, then that exception would need to be caught by every controller where we invoke it so in order to not duplicate the exception handeling
// logic, we write a global exception handler

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest
    ){
    Map<String,String> validationErrors=new HashMap<>();
    List<ObjectError> validationErrorList=ex.getAllErrors();
    validationErrorList.forEach(objectError -> {
        String fieldName=((FieldError)objectError).getField();
        String fieldMessage=objectError.getDefaultMessage();
        validationErrors.put(fieldName,fieldMessage);
    });
    return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
    }
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

    @ExceptionHandler(Exception.class)
    // This annotation will tell the framework that this method will handle General exception
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
