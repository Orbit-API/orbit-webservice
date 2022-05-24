package com.api.orbitwebservice.controllers;

import com.api.orbitwebservice.dtos.UserDTO;
import com.api.orbitwebservice.entities.UserEntity;
import com.api.orbitwebservice.services.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public UsersController(UsersService usersService, PasswordEncoder encoder) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody Map<String, String> body) {
        UserDTO userDTO = new UserDTO(
                body.get("name"),
                body.get("email"),
                body.get("password"),
                body.get("phone"));
        try {
            UserEntity user = this.usersService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @GetMapping
    public ResponseEntity<Page<UserEntity>> getAllUsers(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.findAll(pageable));
    }
}
