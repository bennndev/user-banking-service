package com.lab10.user_banking_service.adapter.out.persistence;

import org.springframework.stereotype.Component;
import com.lab10.user_banking_service.domain.User;
import com.lab10.user_banking_service.application.port.out.UserRepositoryPort;
import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public void save(User user) {
        UserEntity entity = toEntity(user);
        userRepository.save(entity);
    }

    private User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getEmail());
    }

    private UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setEmail(domain.getEmail());
        return entity;
    }
}
