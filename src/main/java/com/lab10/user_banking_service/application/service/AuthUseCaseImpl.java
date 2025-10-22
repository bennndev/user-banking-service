package com.lab10.user_banking_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.lab10.user_banking_service.domain.User;
import com.lab10.user_banking_service.application.port.in.AuthUseCase;
import com.lab10.user_banking_service.application.port.out.UserRepositoryPort;
// Asumir JwtUtil clase para generar/validar tokens

@Service
public class AuthUseCaseImpl implements AuthUseCase {
    private final UserRepositoryPort userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // private final JwtUtil jwtUtil; // Inyectar para JWT

    public AuthUseCaseImpl(UserRepositoryPort userRepository /*, JwtUtil jwtUtil*/) {
        this.userRepository = userRepository;
        // this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // return jwtUtil.generateToken(user.getId().toString());
        return "jwt-token-placeholder-for-" + username; // Placeholder
    }

    @Override
    public void register(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(java.util.UUID.randomUUID(), username, hashedPassword, email);
        userRepository.save(newUser);
    }

    @Override
    public void logout(String token) {
        // Lógica para invalidar token (e.g., agregar a blacklist en Redis o memoria)
        System.out.println("Token invalidated: " + token);
    }
}