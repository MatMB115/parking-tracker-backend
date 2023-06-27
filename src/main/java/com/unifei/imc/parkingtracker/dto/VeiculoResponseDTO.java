package com.unifei.imc.parkingtracker.dto;

import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;

public record VeiculoResponseDTO(Integer id_veiculo, Integer id_cliente, String placa, String modelo, String cor) {
    public VeiculoResponseDTO(Veiculo veiculo){
        this(veiculo.getIdVeiculo(), veiculo.getIdCliente(), veiculo.getPlaca(), veiculo.getModelo(), veiculo.getCor());
    }
}
