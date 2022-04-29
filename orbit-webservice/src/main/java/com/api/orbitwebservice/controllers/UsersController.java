package com.api.orbitwebservice.controllers;

import com.api.orbitwebservice.dtos.UserDTO;
import com.api.orbitwebservice.entities.UserEntity;
import com.api.orbitwebservice.services.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    private final PasswordEncoder encoder;

    public UsersController(UsersService usersService, PasswordEncoder encoder) {
        this.usersService = usersService;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody Map<String, String> body) {
        Long startTime = new Date().getTime();
//        Optional<UserEntity> emailAlreadyInUse = usersService.findByEmail(userDTO.getEmail());

        UserDTO userDTO = new UserDTO(
                body.get("name"),
                body.get("email"),
                body.get("password"),
                body.get("phone"));

//        if (!emailAlreadyInUse.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
//        }

        var userEntity = new UserEntity();

        BeanUtils.copyProperties(userDTO, userEntity);

        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userEntity.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        userEntity.setUpdated_at(LocalDateTime.now(ZoneId.of("UTC")));

        this.usersService.save(userEntity);
        userEntity.setCreationTimeMili(new Date().getTime() - startTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findAll());
    }
}
