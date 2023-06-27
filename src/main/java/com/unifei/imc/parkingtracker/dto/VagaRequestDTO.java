package com.unifei.imc.parkingtracker.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record VagaRequestDTO(Optional<Integer> id_veiculo, Optional<Integer> id_cliente, Integer status, Double lat, Double longi, Optional<LocalDateTime> lastModification, Optional<LocalDateTime> reservation) {
}
