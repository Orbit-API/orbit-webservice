package com.api.orbitwebservice.repositories;

import com.api.orbitwebservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByEmail(String email);
}