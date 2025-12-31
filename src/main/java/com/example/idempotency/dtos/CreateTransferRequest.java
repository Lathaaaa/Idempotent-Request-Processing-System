package com.example.idempotency.dtos;


import lombok.Data;

@Data
public class CreateTransferRequest {

    private String debtorAccount;
    private String creditorAccount;
    private Double amount;
    private String currency;
    private String reference;

    // getters & setters
}
