package com.microservices.loans;

import com.microservices.loans.DTO.LoansConfigurationDTO;
import com.microservices.loans.entities.Loans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingLoan")
@EnableConfigurationProperties(value={LoansConfigurationDTO.class})
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
