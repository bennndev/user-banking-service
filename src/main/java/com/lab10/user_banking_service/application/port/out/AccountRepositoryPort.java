package com.lab10.user_banking_service.application.port.out;

import com.lab10.user_banking_service.domain.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepositoryPort {
    Optional<Account> findById(UUID id);
    void save(Account account);
    Optional<Account> findByUserId(String userId);
}
