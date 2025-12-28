package com.microservices.loans.exceptions;

import com.microservices.loans.DTO.ErrorResponseDTO;
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
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errorMap=new HashMap<>();
        List<ObjectError> errorList=ex.getAllErrors();
        errorList.forEach(objectError -> {
            String fieldName=((FieldError)objectError).getField();
            String fieldValue= objectError.getDefaultMessage();
            errorMap.put(fieldName,fieldValue);
        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
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
