package com.lab10.user_banking_service.application.service;

import org.springframework.stereotype.Service;
import com.lab10.user_banking_service.domain.*;
import com.lab10.user_banking_service.application.port.in.SendMoneyUseCase;
import com.lab10.user_banking_service.application.port.out.AccountRepositoryPort;
import com.lab10.user_banking_service.application.port.out.NotificationPort;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class SendMoneyUseCaseImpl implements SendMoneyUseCase {
    private final AccountRepositoryPort accountRepository;
    private final NotificationPort notificationPort;

    public SendMoneyUseCaseImpl(AccountRepositoryPort accountRepository, NotificationPort notificationPort) {
        this.accountRepository = accountRepository;
        this.notificationPort = notificationPort;
    }

    @Override
    public Transfer sendMoney(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("From account not found"));
        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("To account not found"));

        from.debit(amount);
        to.credit(amount);

        accountRepository.save(from);
        accountRepository.save(to);

        Transfer transfer = new Transfer(UUID.randomUUID(), fromAccountId, toAccountId, amount);
        notificationPort.sendNotification("Transfer completed: " + amount);

        return transfer;
    }
}
