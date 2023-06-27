package com.unifei.imc.parkingtracker.service;

import com.unifei.imc.parkingtracker.dto.VagaResponseDTO;
import com.unifei.imc.parkingtracker.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;
    public List<VagaResponseDTO> getAllUser() {
        List<VagaResponseDTO> listVagas = vagaRepository.findAll().stream().map(VagaResponseDTO::new).toList();
        return listVagas;
    }
}
