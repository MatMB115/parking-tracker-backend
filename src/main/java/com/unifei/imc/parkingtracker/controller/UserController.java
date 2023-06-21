package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.user.User;
import com.unifei.imc.parkingtracker.user.UserRepository;
import com.unifei.imc.parkingtracker.user.UserRequestDTO;
import com.unifei.imc.parkingtracker.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;
    @PostMapping
    public void saveUser(@RequestBody UserRequestDTO data){
        User userData = new User(data);
        repository.save(userData);
        return;
    }
    @GetMapping
    public List<UserResponseDTO> getAll(){
        List<UserResponseDTO> listUsers = repository.findAll().stream().map(UserResponseDTO::new).toList();
        return listUsers;
    }
}
