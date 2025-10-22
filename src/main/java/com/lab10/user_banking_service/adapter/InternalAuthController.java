package com.lab10.user_banking_service.adapter;

import org.springframework.web.bind.annotation.*;
import com.lab10.user_banking_service.application.port.in.AuthUseCase;

@RestController
@RequestMapping("/internal/auth")
public class InternalAuthController {
    private final AuthUseCase authUseCase;

    public InternalAuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authUseCase.register(request.getUsername(), request.getPassword(), request.getEmail());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authUseCase.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        authUseCase.logout(token.replace("Bearer ", ""));
    }

    @GetMapping("/transfer-form")
    public String transferForm(@RequestHeader("Authorization") String token) {
        // validateToken(token);  // Proteger con JWT
        return "<html><body><h2>Transfer Form</h2><form action='/api/transfers' method='post'>From Account ID: <input type='text' name='fromAccountId'><br>To Account ID: <input type='text' name='toAccountId'><br>Amount: <input type='number' step='0.01' name='amount'><br><button type='submit'>Transfer</button></form></body></html>";
    }
}

// DTOs
class RegisterRequest {
    private String username;
    private String password;
    private String email;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class LoginRequest {
    private String username;
    private String password;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
