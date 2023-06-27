package com.unifei.imc.parkingtracker.entity.vehicle;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    @Setter(AccessLevel.NONE)
    private Integer idVeiculo;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "placa")
    private String placa;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "cor")
    private String cor;
}