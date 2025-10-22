package com.lab10.user_banking_service.adapter.out.persistence;

import com.lab10.user_banking_service.application.port.out.AccountRepositoryPort;
import org.springframework.stereotype.Component;
import com.lab10.user_banking_service.domain.Account;
import java.util.UUID;
import java.util.Optional;

@Component
public class AccountPersistenceAdapter implements AccountRepositoryPort {
    private final AccountRepository accountRepository;

    public AccountPersistenceAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void save(Account account) {
        AccountEntity entity = toEntity(account);
        accountRepository.save(entity);
    }

    @Override
    public Optional<Account> findByUserId(String userId) {
        return accountRepository.findByUserId(userId).map(this::toDomain);
    }

    private Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getUserId(),
                entity.getBalance()
        );
    }

    private AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setUserId(account.getUserId());
        entity.setBalance(account.getBalance());
        entity.setCurrency(account.getCurrency());
        return entity;
    }
}
