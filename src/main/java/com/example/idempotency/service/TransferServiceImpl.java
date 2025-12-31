package com.example.idempotency.service;

import com.example.idempotency.dtos.*;
import com.example.idempotency.entity.*;
import com.example.idempotency.repository.*;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    private final FundTransferRepository transferRepo;
    private final IdempotentRequestRepository idemRepo;

    public TransferServiceImpl(FundTransferRepository transferRepo,
                               IdempotentRequestRepository idemRepo) {
        this.transferRepo = transferRepo;
        this.idemRepo = idemRepo;
    }

    @Override
    public TransferResponse createTransfer(String key, CreateTransferRequest request) {

        // 1️⃣ If request already processed → return existing transfer
        var existing = idemRepo.findByIdempotencyKey(key);
        if (existing.isPresent()) {
            FundTransfer ft = transferRepo
                    .findById(existing.get().getTransferId())
                    .orElseThrow();
            return toResponse(ft);
        }

        // 2️⃣ Create new transfer
        FundTransfer ft = new FundTransfer();
        ft.setDebtorAccount(request.getDebtorAccount());
        ft.setCreditorAccount(request.getCreditorAccount());
        ft.setAmount(request.getAmount());
        ft.setCurrency(request.getCurrency());
        ft.setReference(request.getReference());
        ft.setStatus("PENDING");

        FundTransfer saved = transferRepo.save(ft);

        // 3️⃣ Store idempotency mapping
        idemRepo.save(new IdempotentRequest(key, saved.getTransferId()));

        return toResponse(saved);
    }

    @Override
    public TransferResponse updateStatus(String id, String status) {

        FundTransfer ft = transferRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transfer not found"));

        ft.setStatus(status);
        FundTransfer saved = transferRepo.save(ft);

        return toResponse(saved);
    }

    private TransferResponse toResponse(FundTransfer ft) {
        TransferResponse r = new TransferResponse();
        r.setTransferId(ft.getTransferId());
        r.setDebtorAccount(ft.getDebtorAccount());
        r.setCreditorAccount(ft.getCreditorAccount());
        r.setAmount(ft.getAmount());
        r.setCurrency(ft.getCurrency());
        r.setReference(ft.getReference());
        r.setStatus(ft.getStatus());
        r.setCreatedAt(ft.getCreatedAt());
        return r;
    }
}
