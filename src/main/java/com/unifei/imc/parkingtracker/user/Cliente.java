package com.unifei.imc.parkingtracker.user;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "creditos")
    private Integer creditos;

    // getters and setters
}