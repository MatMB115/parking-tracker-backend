package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.user.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenteRepository extends JpaRepository<Agente, Integer> {
    Agente findByUserId(Integer idUser);
}