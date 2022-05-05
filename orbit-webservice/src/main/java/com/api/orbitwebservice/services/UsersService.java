package com.api.orbitwebservice.services;

import com.api.orbitwebservice.entities.UserEntity;
import com.api.orbitwebservice.repositories.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return usersRepository.save(userEntity);
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
