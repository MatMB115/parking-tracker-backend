package com.unifei.imc.parkingtracker.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_agente")
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agente")
    private Integer idAgente;

    @Column(name = "turno")
    private Character turno;

    @OneToOne
    @JoinColumn(name = "id_rel_user")
    private User user;
}