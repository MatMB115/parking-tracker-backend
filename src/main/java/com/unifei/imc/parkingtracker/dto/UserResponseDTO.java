package com.unifei.imc.parkingtracker.dto;

import com.unifei.imc.parkingtracker.user.Agente;
import com.unifei.imc.parkingtracker.user.Cliente;

public record UserResponseDTO(Integer idUser, Integer idChild, String nome, String email, String senha, String contato, Character tipoUsuario, Integer creditos, Character turno) {
    public UserResponseDTO(Cliente cliente){
        this(cliente.getUser().getId(), cliente.getIdCliente(), cliente.getUser().getNome(), cliente.getUser().getEmail(), cliente.getUser().getSenha(), cliente.getUser().getContato(), cliente.getUser().getTipoUsuario(), cliente.getCreditos(), null);
    }
    public UserResponseDTO(Agente agente){
        this(agente.getUser().getId(), agente.getIdAgente(), agente.getUser().getNome(), agente.getUser().getEmail(), agente.getUser().getSenha(), agente.getUser().getContato(), agente.getUser().getTipoUsuario(), null, agente.getTurno());
    }
}
