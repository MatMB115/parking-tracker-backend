package com.unifei.imc.parkingtracker.user;

public record UserRequestDTO(String nome, String email, String senha, String contato, String tipoUsuario, Integer creditos, String turno) {
}
