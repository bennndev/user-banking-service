package com.lab10.user_banking_service.application.port.in;

public interface AuthUseCase {
    String login(String username, String password); // Retorna JWT
    void register(String username, String password, String email);
    void logout(String token); // Invalida token
}
