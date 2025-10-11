package com.microservices.loans.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String apiPath;
    private HttpStatus httpStatus;
    private String statusMsg;
    private LocalDateTime errorTime;
}
