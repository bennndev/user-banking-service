package com.lab10.user_banking_service.adapter;

import org.springframework.web.bind.annotation.*;
import com.lab10.user_banking_service.application.port.in.SendMoneyUseCase;
import com.lab10.user_banking_service.domain.Transfer;
import java.util.UUID;
import java.math.BigDecimal;

@RestController
@RequestMapping("/internal/transfers")  // Endpoint interno
public class InternalTransferController {
    private final SendMoneyUseCase sendMoneyUseCase;

    public InternalTransferController(SendMoneyUseCase sendMoneyUseCase) {
        this.sendMoneyUseCase = sendMoneyUseCase;
    }

    @PostMapping
    public Transfer transfer(@RequestBody TransferRequest request, @RequestHeader("Authorization") String token) {
        // Validar JWT internamente (implementar JwtUtil.validateToken)
        // validateToken(token);
        return sendMoneyUseCase.sendMoney(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
    }
}

// DTO para request
class TransferRequest {
    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;
    // Getters y setters...
    public UUID getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(UUID fromAccountId) { this.fromAccountId = fromAccountId; }
    public UUID getToAccountId() { return toAccountId; }
    public void setToAccountId(UUID toAccountId) { this.toAccountId = toAccountId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}

