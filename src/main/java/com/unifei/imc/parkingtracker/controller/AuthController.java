package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.LoginRequestDTO;
import com.unifei.imc.parkingtracker.dto.LoginResponseDTO;
import com.unifei.imc.parkingtracker.dto.UserRequestDTO;
import com.unifei.imc.parkingtracker.dto.VeiculoRequestDTO;
import com.unifei.imc.parkingtracker.infra.security.TokenService;
import com.unifei.imc.parkingtracker.repository.UserRepository;
import com.unifei.imc.parkingtracker.service.UserService;
import com.unifei.imc.parkingtracker.service.VeiculoService;
import com.unifei.imc.parkingtracker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    private VeiculoService veiculoService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequestDTO data){
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var user = ((User) auth.getPrincipal());
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getId(), token));
        } catch(InternalAuthenticationServiceException e){
            return ResponseEntity.status(404).body(Map.of("details", "Usuario não encontrado"));
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated UserRequestDTO data){
        if(this.repository.findByEmail(data.email())!= null) return ResponseEntity.badRequest().build();

        Integer idCliente = userService.saveUser(data);
        if(idCliente != null){
            VeiculoRequestDTO veiculoDTO = new VeiculoRequestDTO(idCliente, data.placa(), data.modelo(), data.cor());
            veiculoService.saveVeiculo(veiculoDTO);
        }
        return ResponseEntity.ok().build();
    }
}
