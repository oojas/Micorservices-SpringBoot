package com.microservices.loans.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends AuditingEntityListener{
    @Column(updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(insertable = false)
    @LastModifiedDate
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;
}
