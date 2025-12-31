package com.example.idempotency.service;

import com.example.idempotency.dtos.CreateTransferRequest;
import com.example.idempotency.dtos.TransferResponse;

public interface TransferService {

    TransferResponse createTransfer(String key, CreateTransferRequest request);

    TransferResponse updateStatus(String transferId, String status);
}
