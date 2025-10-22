package com.lab10.user_banking_service.application.port.in;

import com.lab10.user_banking_service.domain.Transfer;
import java.math.BigDecimal;
import java.util.UUID;

public interface SendMoneyUseCase {
    Transfer sendMoney(UUID fromAccountId, UUID toAccountId, BigDecimal amount);
}
