package com.unifei.imc.parkingtracker.controller;

import com.unifei.imc.parkingtracker.dto.VeiculoRequestDTO;
import com.unifei.imc.parkingtracker.dto.VeiculoResponseDTO;
import com.unifei.imc.parkingtracker.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vehicle")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<VeiculoResponseDTO> getAll(){
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> getByIdCliente(@PathVariable String id) {
        Optional<VeiculoResponseDTO> veiculoDTO = veiculoService.getVeiculoById(id);
        return veiculoDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVeiculo(@PathVariable String id, @RequestBody VeiculoRequestDTO data) {
        Optional<VeiculoResponseDTO> veiculoDTO = veiculoService.getVeiculoById(id) ;
        if (veiculoDTO.isPresent()) {
            Integer idVeiculo = veiculoDTO.get().id_veiculo();
            veiculoService.updateVeiculo(idVeiculo, data);
            return ResponseEntity.status(200).body("Veículo atualizado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        Optional<VeiculoResponseDTO> veiculoDTO = veiculoService.getVeiculoById(id);
        if (veiculoDTO.isPresent()) {
            Integer idVeiculo = veiculoDTO.get().id_veiculo();
            veiculoService.deleteVeiculo(idVeiculo);
            return ResponseEntity.status(200).body("Veículo deletado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }

}
