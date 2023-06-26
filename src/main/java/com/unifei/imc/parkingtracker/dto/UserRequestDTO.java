package com.unifei.imc.parkingtracker.dto;

public record UserRequestDTO(String nome, String email, String senha, String contato, Character tipoUsuario, Integer creditos, Character turno, String cor, String modelo, String placa) {

}
