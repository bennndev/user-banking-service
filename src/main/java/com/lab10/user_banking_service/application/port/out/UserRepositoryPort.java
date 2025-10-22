package com.lab10.user_banking_service.application.port.out;

import com.lab10.user_banking_service.domain.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    void save(User user);
}
