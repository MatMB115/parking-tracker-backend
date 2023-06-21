package com.unifei.imc.parkingtracker.user;

import jakarta.persistence.*;
@Entity
@Table(name = "tb_agente")
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agente")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "turno")
    private Character turno;
    // getters and setters
}