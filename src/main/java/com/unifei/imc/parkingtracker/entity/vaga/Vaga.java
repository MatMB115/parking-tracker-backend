package com.unifei.imc.parkingtracker.entity.vaga;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_vagas")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    @Setter(AccessLevel.NONE)
    private Integer idVagas;

    @Column(name = "id_veiculo", nullable = true)
    private Integer idVeiculo;

    @Column(name = "id_cliente", nullable = true)
    private Integer idCliente;

    @Column(name = "status")
    private Integer status;

    @Column(name = "position_lat")
    private Double latidade;

    @Column(name = "position_long")
    private Double longitude;

    @Column(name = "last_modificated")
    private LocalDateTime lastModification;

    @Column(name = "reservation_time", nullable = true)
    private LocalDateTime reservationTime;
}
