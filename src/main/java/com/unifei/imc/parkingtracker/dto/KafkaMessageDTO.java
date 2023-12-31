package com.unifei.imc.parkingtracker.dto;

import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;

public record KafkaMessageDTO(Integer id_vaga, Veiculo veiculo, Integer status, Double lat, Double longi, String lastModification, String reservation) {
}
