package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.vaga.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {

}
