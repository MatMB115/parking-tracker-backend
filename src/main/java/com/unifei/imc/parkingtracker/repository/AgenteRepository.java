package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.user.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, Integer> {
    Agente findByUserId(Integer idUser);
}
