package com.example.idempotency.controller;

import com.example.idempotency.dtos.*;
import com.example.idempotency.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public TransferResponse create(
            @RequestHeader("Idempotency-Key") String key,
            @RequestBody CreateTransferRequest request) {

        return service.createTransfer(key, request);
    }

    // STATUS UPDATE ONLY (PATCH, not PUT)
    @PatchMapping("/{id}/status")
    public TransferResponse updateStatus(
            @PathVariable String id,
            @RequestBody UpdateStatusRequest request) {

        return service.updateStatus(id, request.getStatus());
    }
}
