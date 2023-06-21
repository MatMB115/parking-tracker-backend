package com.unifei.imc.parkingtracker.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "contato")
    private String contato;

    @Column(name = "tipo_usuario")
    private Character tipoUsuario;

    public User(UserRequestDTO data){
        this.nome = data.nome();
        this.email = data.email();
        this.senha = data.senha();
        this.contato = data.contato();
        this.tipoUsuario = data.tipo();
    }
}