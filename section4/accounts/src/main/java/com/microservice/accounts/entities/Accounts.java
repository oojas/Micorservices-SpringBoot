package com.microservice.accounts.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity // Treat this class as the POJO representation of the table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{
    @Id
    @Column(updatable = false)
    private Long accountNumber;
    @Column(updatable = false)
    private Long customerId;
    @Column
    private String accountType;
    @Column
    private String branchAddress;
}
