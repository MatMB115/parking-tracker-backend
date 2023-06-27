package com.unifei.imc.parkingtracker.dto;

import com.unifei.imc.parkingtracker.entity.vaga.Vaga;
import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;

import java.time.LocalDateTime;
import java.util.Optional;

public record VagaResponseDTO(Integer id_vaga, Optional<Integer> id_veiculo, Optional<Integer> id_cliente, Integer status, Double lat, Double longi, LocalDateTime lastModification, Optional<LocalDateTime> reservation) {

    public VagaResponseDTO(Vaga vaga) {
        this(
                vaga.getIdVagas(),
                Optional.ofNullable(vaga.getIdVeiculo()),
                Optional.ofNullable(vaga.getIdCliente()),
                vaga.getStatus(),
                vaga.getLatidade(),
                vaga.getLongitude(),
                vaga.getLastModification(),
                Optional.ofNullable(vaga.getReservationTime()));
    }

}
