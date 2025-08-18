package com.bivago_api.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bivago_api.domain.models.User;

public interface IUserRepository {
    
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(User user);
    void deleteById(UUID id);

    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

}
