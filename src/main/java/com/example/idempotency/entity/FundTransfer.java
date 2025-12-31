package com.example.idempotency.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FundTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transferId;

    @Column(updatable = false, nullable = false)
    private String debtorAccount;

    @Column(updatable = false, nullable = false)
    private String creditorAccount;

    @Column(updatable = false, nullable = false)
    private Double amount;

    @Column(updatable = false, nullable = false)
    private String currency;

    @Column(updatable = false)
    private String reference;

    @Column(nullable = false)
    private String status;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters & setters
}
