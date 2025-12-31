package com.example.idempotency.dtos;



import lombok.Data;

import java.time.LocalDateTime;



@Data
public class TransferResponse {

    private String transferId;
    private String debtorAccount;
    private String creditorAccount;
    private Double amount;
    private String currency;
    private String reference;
    private String status;
    private LocalDateTime createdAt;

    // getters & setters
}
