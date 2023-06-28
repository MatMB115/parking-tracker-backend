package com.unifei.imc.parkingtracker.entity.infracoes;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_autuacao")
public class Autuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autuacao")
    @Setter(AccessLevel.NONE)
    private Integer idAtuacao;

    @Column(name = "id_agente")
    private Integer idAgente;

    @Column(name = "placa")
    private String placa;
}
