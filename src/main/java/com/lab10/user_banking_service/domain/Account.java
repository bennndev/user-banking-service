package com.lab10.user_banking_service.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID id;
    private String userId; // Vinculado a usuario autenticado
    private BigDecimal balance;
    private String currency = "USD";

    // Constructores
    public Account(UUID id, String userId, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }
    // Lógica de dominio
    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    // Getters y setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

