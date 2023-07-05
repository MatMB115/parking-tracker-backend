package com.unifei.imc.parkingtracker.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifei.imc.parkingtracker.dto.KafkaMessageDTO;
import com.unifei.imc.parkingtracker.dto.KafkaMsgInfraDTO;
import com.unifei.imc.parkingtracker.service.AutuacaoService;
import com.unifei.imc.parkingtracker.service.LogService;
import com.unifei.imc.parkingtracker.service.VagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {
    @Autowired
    private VagaService vagaService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AutuacaoService autuacaoService;
    @Autowired
    private LogService logService;
    @KafkaListener(topics = "${topic.name.consumer}", clientIdPrefix = "status")
    public void consume(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        logInfo(payload);
        KafkaMessageDTO vagaStats = objectMapper.readValue(payload.value(), KafkaMessageDTO.class);

        vagaService.updateVagaStatus(vagaStats);
    }

    @KafkaListener(topics = "${topic.name.consumer.agent}", clientIdPrefix = "agente")
    public void consumeAgente(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        logInfo(payload);
        KafkaMsgInfraDTO infra = objectMapper.readValue(payload.value(), KafkaMsgInfraDTO.class);

        autuacaoService.autuaVeiculo(infra);
    }

    @KafkaListener(topics = "${topic.name.consumer.check}", clientIdPrefix = "check")
    public void consumeCheckStatus(ConsumerRecord<String, String> payload) {
        logInfo(payload);
    }
    private void logInfo(ConsumerRecord<String, String> payload) {
        log.info("Tópico: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        logService.writeInFile(payload);
    }

}