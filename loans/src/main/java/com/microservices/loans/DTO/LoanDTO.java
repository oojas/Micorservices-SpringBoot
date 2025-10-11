package com.microservices.loans.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class LoanDTO {
    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Please enter a 10 digit valid Mobile Number")
    private String mobileNumber;
    private String loanNumber;
    @NotEmpty(message = "Loan Type cannot be empty")
    private String loanType;
    @NotNull(message = "Total Loan Amount cannot be empty")
    private Long totalLoan;
    @NotNull(message = "Amount paid cannot be null")
    private Long amountPaid;
    @NotNull(message = "OutStanding Amount cannot be null")
    private Long outstandingAmount;
}
