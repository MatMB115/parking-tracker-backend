package com.unifei.imc.parkingtracker.service;
import com.unifei.imc.parkingtracker.dto.UserRequestDTO;
import com.unifei.imc.parkingtracker.dto.UserResponseDTO;
import com.unifei.imc.parkingtracker.repository.AgenteRepository;
import com.unifei.imc.parkingtracker.repository.ClienteRepository;
import com.unifei.imc.parkingtracker.repository.UserRepository;
import com.unifei.imc.parkingtracker.entity.user.Agente;
import com.unifei.imc.parkingtracker.entity.user.Cliente;
import com.unifei.imc.parkingtracker.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class UserService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgenteRepository agenteRepository;
    @Autowired
    private UserRepository userRepository;

    public Integer saveUser(UserRequestDTO data) {

        User user = new User();
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = passEncoder.encode(data.senha());
        user.setSenha(senhaCriptografada);
        user.setNome(data.nome());
        user.setEmail(data.email());
        user.setContato(data.contato());
        user.setTipoUsuario(data.tipoUsuario());

        userRepository.save(user);

        if (data.tipoUsuario().toString().equals("C")) {
            Cliente cliente = new Cliente();
            cliente.setUser(user);
            cliente.setCreditos(data.creditos());
            clienteRepository.save(cliente);
            return cliente.getIdCliente();
        } else if (data.tipoUsuario().toString().equals("A")) {
            Agente agente = new Agente();
            agente.setUser(user);
            agente.setTurno(data.turno());
            agenteRepository.save(agente);
            return null;
        }
        return null;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> listUser = clienteRepository.findAll().stream().map(UserResponseDTO::new).toList();
        List<UserResponseDTO> listAgente = agenteRepository.findAll().stream().map(UserResponseDTO::new).toList();
        List<UserResponseDTO> DTOUsers = new ArrayList<>();
        DTOUsers.addAll(listUser);
        DTOUsers.addAll(listAgente);

        return DTOUsers;
    }

    public Optional<UserResponseDTO> getUserById(String id) {
        Integer idUser = parseInt(id);

        Optional<Cliente> optionalClient = Optional.ofNullable(clienteRepository.findByUserId(idUser));

        if (optionalClient.isPresent()) {
            Cliente cliente = optionalClient.get();
            UserResponseDTO userDTO = new UserResponseDTO(cliente);
            return Optional.of(userDTO);
        } else {
            Optional<Agente> optionalAgente = Optional.ofNullable(agenteRepository.findByUserId(idUser));
            if (optionalAgente.isPresent()) {
                Agente agente = optionalAgente.get();
                UserResponseDTO userDTO = new UserResponseDTO(agente);
                return Optional.of(userDTO);
            }
        }
        return Optional.empty();
    }

    public void updateUser(String id, UserRequestDTO data) {
        Integer idUser = parseInt(id);

        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
            User user = optionalUser.get();
            user.setNome(data.nome());
            user.setEmail(data.email());
            String senhaCriptografada = passEncoder.encode(data.senha());
            user.setSenha(senhaCriptografada);
            user.setContato(data.contato());
            user.setTipoUsuario(data.tipoUsuario());

            userRepository.save(user);
        }
    }

    public void deleteUser(String id) {
        Integer idUser = parseInt(id);
        userRepository.deleteById(idUser);
    }
}