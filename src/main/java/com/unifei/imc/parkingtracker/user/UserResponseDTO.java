package com.unifei.imc.parkingtracker.user;

public record UserResponseDTO(Integer id, String nome, String email, String senha, String contato, Character tipo) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getNome(), user.getEmail(), user.getSenha(), user.getContato(), user.getTipoUsuario());
    }
}
