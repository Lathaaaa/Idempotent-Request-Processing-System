package com.example.idempotency.dtos;


import lombok.Data;



@Data
public class UpdateStatusRequest {
    private String status;
    // getters & setters
}
