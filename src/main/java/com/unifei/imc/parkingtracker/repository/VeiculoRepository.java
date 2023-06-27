package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    @Query("SELECT v FROM Veiculo v JOIN Cliente c ON v.id_cliente = c.idCliente")
    Veiculo findVeiculoByIdCliente(Integer idCliente);
}
