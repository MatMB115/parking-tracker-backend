package com.unifei.imc.parkingtracker.service;

import com.unifei.imc.parkingtracker.dto.VagaResponseDTO;
import com.unifei.imc.parkingtracker.dto.VagaRequestDTO;
import com.unifei.imc.parkingtracker.entity.vaga.Vaga;
import com.unifei.imc.parkingtracker.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;
    public List<VagaResponseDTO> getAllVagas() {
        List<VagaResponseDTO> listVagas = vagaRepository.findAll().stream().map(VagaResponseDTO::new).toList();
        return listVagas;
    }

    public void saveVaga(VagaRequestDTO data) {

        Vaga vaga = new Vaga();

        if(data.id_cliente().isPresent()){
            vaga.setIdCliente(data.id_cliente().get());
        }
        if(data.reservation().isPresent()){
            vaga.setReservationTime(data.reservation().get());
        }
        if(data.lastModification().isPresent()){
            vaga.setLastModification(data.lastModification().get());
        }
        vaga.setLongitude(data.longi());
        vaga.setLatidade(data.lat());

        vagaRepository.save(vaga);
    }
}
