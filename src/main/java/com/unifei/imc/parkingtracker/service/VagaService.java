package com.unifei.imc.parkingtracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifei.imc.parkingtracker.dto.KafkaMessageDTO;
import com.unifei.imc.parkingtracker.dto.VagaResponseDTO;
import com.unifei.imc.parkingtracker.dto.VagaRequestDTO;
import com.unifei.imc.parkingtracker.entity.vaga.Vaga;
import com.unifei.imc.parkingtracker.kafka.service.TopicProducer;
import com.unifei.imc.parkingtracker.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;
    private List<Vaga> vagasPendentes;
    @Autowired
    private TopicProducer topicProducer;

    public VagaService() {
        this.vagasPendentes = new ArrayList<>();
    }

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

    public void updateVagaStatus(KafkaMessageDTO vagaStats) {
        Optional<Vaga> optionalVaga = vagaRepository.findById(vagaStats.id_vaga());
        if(optionalVaga.isPresent()){
            Vaga vaga = optionalVaga.get();
            vaga.setStatus(vagaStats.status());

            vaga.setLastModification(LocalDateTime.parse(vagaStats.lastModification()));

            if(vagaStats.reservation() != null ){
                vaga.setReservationTime(LocalDateTime.parse(vagaStats.reservation()));
                vaga.setIdCliente(vagaStats.veiculo().getId_cliente());
                vaga.setVeiculo(vagaStats.veiculo());
                if(vagasPendentes.stream().noneMatch(v -> v.getIdVagas().equals(vaga.getIdVagas()))){
                    vagasPendentes.add(vaga);
                }else{
                    vagasPendentes = vagasPendentes.stream().map(v -> {
                       if(v.getIdVagas().equals(vaga.getIdVagas())){
                           return vaga;
                       }
                       return v;
                    }).toList();
                }
                vagaRepository.save(vaga);
            }else{
                vaga.setReservationTime(null);
                vaga.setIdCliente(null);
                vaga.setVeiculo(null);
                vagaRepository.save(vaga);
            }
        }
    }
    @Scheduled(fixedDelay = 10000)
    public void verificaTempoReserva(){
        LocalDateTime now  = LocalDateTime.now();
        List<Integer> removeVagas = new ArrayList<>();

        for(Vaga vaga: vagasPendentes){
            LocalDateTime tempoReserva = vaga.getReservationTime();
            if(tempoReserva.isBefore(now) && vaga.getStatus().equals(1)){
                vaga.setStatus(4);
                vaga.setLastModification(now);
                VagaResponseDTO vagaDTO = new VagaResponseDTO(vaga);
                topicProducer.send(toMap(vagaDTO));
            }
            if(tempoReserva.isBefore(now) && (vaga.getStatus().equals(2) || vaga.getStatus().equals(0))){
                removeVagas.add(vaga.getIdVagas());
            }
        }
        for(Integer idVaga: removeVagas){
            vagasPendentes.removeIf(vaga -> vaga.getIdVagas().equals(idVaga));
        }
    }

    public String toMap(VagaResponseDTO vaga) {
        Map<String, Object> vagaMap = new HashMap<>();

        vagaMap.put("id_vaga", vaga.id_vaga());
        vagaMap.put("status", vaga.status());
        vagaMap.put("lat", vaga.lat());
        vagaMap.put("longi", vaga.longi());
        vagaMap.put("lastModification", vaga.lastModification().toString());
        vagaMap.put("reservation", vaga.reservation()
                .map(LocalDateTime::toString)
                .orElse(null));

        vaga.veiculo().ifPresent(veiculo -> {
            Map<String, Object> veiculoMap = new HashMap<>();
            veiculoMap.put("cor", veiculo.getCor());
            veiculoMap.put("modelo", veiculo.getModelo());
            veiculoMap.put("placa", veiculo.getPlaca());
            veiculoMap.put("id_veiculo", veiculo.getId_veiculo());
            veiculoMap.put("id_cliente", veiculo.getId_veiculo());
            vagaMap.put("veiculo", veiculoMap);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(vagaMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
