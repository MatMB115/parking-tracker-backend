package com.unifei.imc.parkingtracker.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "creditos")
    private Integer creditos;

    @OneToOne
    @JoinColumn(name = "id_rel_user")
    private User user;
}