package com.example.idempotency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.idempotency.entity.IdempotentRequest;
import java.util.Optional;

public interface IdempotentRequestRepository extends JpaRepository<IdempotentRequest, Long> {
    Optional<IdempotentRequest> findByIdempotencyKey(String idempotencyKey);
}
