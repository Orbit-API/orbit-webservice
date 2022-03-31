package com.api.orbitwebservice.services;

import com.api.orbitwebservice.entities.UserEntity;
import com.api.orbitwebservice.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
