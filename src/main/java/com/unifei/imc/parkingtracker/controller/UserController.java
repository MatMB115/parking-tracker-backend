package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgenteRepository agenteRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserResponseDTO data){
        User user = new User();
        user.setNome(data.nome());
        user.setEmail(data.email());
        user.setSenha(data.senha());
        user.setContato(data.contato());
        user.setTipoUsuario(data.tipoUsuario());

        userRepository.save(user);

        if (data.tipoUsuario().toString().equals("C")) {
            Cliente cliente = new Cliente();
            cliente.setUser(user);
            cliente.setCreditos(data.creditos());
            clienteRepository.save(cliente);
        } else if (data.tipoUsuario().toString().equals("A")) {
            Agente agente = new Agente();
            agente.setUser(user);
            agente.setTurno(data.turno());
            agenteRepository.save(agente);
        }

        return ResponseEntity.ok("Usuário e especialização inseridos com sucesso.");
    }
    @GetMapping
    public List<UserResponseDTO> getAll(){
        List<UserResponseDTO> listUser = clienteRepository.findAll().stream().map(UserResponseDTO::new).toList();
        List<UserResponseDTO> listAgente = agenteRepository.findAll().stream().map(UserResponseDTO::new).toList();
        List<UserResponseDTO> DTOUsers = new ArrayList<>();
        DTOUsers.addAll(listUser);
        DTOUsers.addAll(listAgente);

        return DTOUsers;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id){
        Integer idUser = parseInt(id);
        Optional<Cliente> optionalClient = Optional.ofNullable(clienteRepository.findByUserId(idUser));
        if(optionalClient.isPresent()){
            Cliente cliente = optionalClient.get();
            UserResponseDTO userDTO = new UserResponseDTO(cliente);
            return ResponseEntity.ok(userDTO);
        }else{
            Optional<Agente> optionalAgente = Optional.ofNullable(agenteRepository.findByUserId(idUser));
            if(optionalAgente.isPresent()){
                Agente agente = optionalAgente.get();
                UserResponseDTO userDTO = new UserResponseDTO(agente);
                return ResponseEntity.ok(userDTO);
            }
        }
        return ResponseEntity.notFound().build();
    }


}
