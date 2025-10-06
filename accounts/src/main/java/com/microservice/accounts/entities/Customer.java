package com.microservice.accounts.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity // Treat this class as the POJO representation of the table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")// auto generate the customer ID
    private Long customerId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String mobileNumber;
}
