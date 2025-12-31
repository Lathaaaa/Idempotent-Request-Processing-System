package com.example.idempotency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.idempotency.entity.FundTransfer;

public interface FundTransferRepository extends JpaRepository<FundTransfer, String> {}
