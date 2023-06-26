package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.UserRequestDTO;
import com.unifei.imc.parkingtracker.dto.UserResponseDTO;
import com.unifei.imc.parkingtracker.service.UserService; // Import atualizado para o serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id) {
        Optional<UserResponseDTO> userDTO = userService.getUserById(id);
        return userDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserResponseDTO data) {
        Optional<UserResponseDTO> userDTO = userService.getUserById(id);
        if (userDTO.isPresent()) {
            userService.updateUser(id, data);
            return ResponseEntity.ok("Usuário atualizado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
