package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.VagaResponseDTO;
import com.unifei.imc.parkingtracker.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vacancy")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public List<VagaResponseDTO> getAll(){
        return vagaService.getAllUser();
    }
}
