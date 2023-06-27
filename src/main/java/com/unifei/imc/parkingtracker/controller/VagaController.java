package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.VagaResponseDTO;
import com.unifei.imc.parkingtracker.dto.VagaRequestDTO;
import com.unifei.imc.parkingtracker.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacancy")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public List<VagaResponseDTO> getAll(){
        return vagaService.getAllVagas();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody VagaRequestDTO data){
        vagaService.saveVaga(data);
        return ResponseEntity.status(200).body("Vaga criada com sucesso");
    }

}
