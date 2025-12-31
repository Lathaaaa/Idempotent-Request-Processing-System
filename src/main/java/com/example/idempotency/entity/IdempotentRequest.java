package com.example.idempotency.entity;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idempotencyKey"))
public class IdempotentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    @Column(nullable = false)
    private String transferId;

    public IdempotentRequest() {}

    public IdempotentRequest(String key, String transferId) {
        this.idempotencyKey = key;
        this.transferId = transferId;
    }

    public String getTransferId() {
        return transferId;
    }
}
