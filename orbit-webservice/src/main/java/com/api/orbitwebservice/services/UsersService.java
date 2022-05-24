package com.api.orbitwebservice.services;

import com.api.orbitwebservice.dtos.UserDTO;
import com.api.orbitwebservice.entities.UserEntity;
import com.api.orbitwebservice.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder encoder;

    final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UserEntity save(UserDTO userDTO) {
        Optional<UserEntity> emailAlreadyInUse = this.findByEmail(userDTO.getEmail());
        if (!emailAlreadyInUse.isEmpty()) {
            throw new RuntimeException("Email is already in use");
        }
        var userEntity = new UserEntity();

        BeanUtils.copyProperties(userDTO, userEntity);

        userEntity.setPassword(this.encoder.encode(userEntity.getPassword()));
        userEntity.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        userEntity.setUpdated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return usersRepository.save(userEntity);
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
