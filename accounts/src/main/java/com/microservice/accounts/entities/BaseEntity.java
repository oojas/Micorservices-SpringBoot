package com.microservice.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@MappedSuperclass // This is because these are the framework level columns
public class BaseEntity {
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private String createdBy;
    @Column(insertable = false) // we dont want to insert value in this while creating a record
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    private String updatedBy;
}
