package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.LoginRequestDTO;
import com.unifei.imc.parkingtracker.dto.LoginResponseDTO;
import com.unifei.imc.parkingtracker.dto.UserRequestDTO;
import com.unifei.imc.parkingtracker.infra.security.TokenService;
import com.unifei.imc.parkingtracker.repository.UserRepository;
import com.unifei.imc.parkingtracker.service.UserService;
import com.unifei.imc.parkingtracker.user.User;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = ((User) auth.getPrincipal());
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getId(), token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserRequestDTO data){
        if(this.repository.findByEmail(data.email())!=null) return ResponseEntity.badRequest().build();

        userService.saveUser(data);

        return ResponseEntity.ok().build();
    }
}
