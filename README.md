This project demonstrates idempotent request handling in a Spring Boot application using a real-world fund transfer use case.
Idempotency ensures that multiple identical requests produce the same result and do not cause duplicate processing, even if a client retries due to network failures or timeouts

Features
Idempotent API design using Idempotency-Key
Safe retry handling for POST and PATCH requests
Persistent storage of request state
Prevents duplicate fund transfers
Clean layered architecture (Controller → Service → Repository)

How It Works

Client sends a request with an Idempotency-Key
Server checks if the key already exists
If yes → returns stored response
If no → processes request and stores result

Run Locally
mvn clean install
mvn spring-boot:run

App runs on:
http://localhost:8080
