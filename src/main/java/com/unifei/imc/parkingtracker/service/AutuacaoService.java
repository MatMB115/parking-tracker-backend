package com.unifei.imc.parkingtracker.service;

import com.unifei.imc.parkingtracker.dto.KafkaMsgInfraDTO;
import com.unifei.imc.parkingtracker.entity.infracoes.Autuacao;
import com.unifei.imc.parkingtracker.repository.AutuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutuacaoService {
    @Autowired
    private AutuacaoRepository autuacaoRepository;

    public void autuaVeiculo(KafkaMsgInfraDTO vaga) {
        Autuacao autua = new Autuacao();
        autua.setIdAgente(vaga.idAgente());
        autua.setPlaca(vaga.placa());
        autuacaoRepository.save(autua);
    }
}
