package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Veiculo findVeiculoByIdCliente(Integer idCliente);
}
