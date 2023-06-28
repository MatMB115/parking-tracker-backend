package com.unifei.imc.parkingtracker.repository;

import com.unifei.imc.parkingtracker.entity.infracoes.Autuacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutuacaoRepository extends JpaRepository<Autuacao, Integer> {

}
