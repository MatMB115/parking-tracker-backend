package com.unifei.imc.parkingtracker.service;

import com.unifei.imc.parkingtracker.dto.VeiculoRequestDTO;
import com.unifei.imc.parkingtracker.dto.VeiculoResponseDTO;
import com.unifei.imc.parkingtracker.repository.ClienteRepository;
import com.unifei.imc.parkingtracker.repository.VeiculoRepository;
import com.unifei.imc.parkingtracker.entity.vehicle.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class VeiculoService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoResponseDTO> getAllVeiculos(){
        List<VeiculoResponseDTO> listVeiculos = veiculoRepository.findAll().stream().map(VeiculoResponseDTO::new).toList();
        return listVeiculos;
    }

    public void saveVeiculo(VeiculoRequestDTO data) {

        Veiculo veiculo = new Veiculo();
        veiculo.setIdCliente(data.id_cliente());
        veiculo.setCor(data.cor());
        veiculo.setPlaca(data.placa());
        veiculo.setModelo(data.modelo());

        veiculoRepository.save(veiculo);
    }

    public Optional<VeiculoResponseDTO> getVeiculoById(String id) {
        Integer idClienteVeiculo = parseInt(id);

        Optional<Veiculo> optionalVeiculo = Optional.ofNullable(veiculoRepository.findVeiculoByIdCliente(idClienteVeiculo));
        if(optionalVeiculo.isPresent()){
            Veiculo veiculo = optionalVeiculo.get();
            VeiculoResponseDTO veiculoDTO = new VeiculoResponseDTO(veiculo);
            return Optional.of(veiculoDTO);
        }
        return Optional.empty();
    }

    public void updateVeiculo(Integer id, VeiculoRequestDTO data) {

        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            veiculo.setModelo(data.modelo());
            veiculo.setCor(data.cor());
            veiculo.setPlaca(data.placa());

            veiculoRepository.save(veiculo);
        }

    }

    public void deleteVeiculo(Integer id) {
        veiculoRepository.deleteById(id);
    }
}
