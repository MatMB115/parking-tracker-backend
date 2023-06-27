package com.unifei.imc.parkingtracker.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifei.imc.parkingtracker.dto.KafkaMessageDTO;
import com.unifei.imc.parkingtracker.service.VagaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {
    @Value("${topic.name.consumer}")
    private String topicName;
    @Autowired
    private VagaService vagaService;

    @KafkaListener(topics = "${topic.name.consumer}")
    public void consume(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

        ObjectMapper objectMapper = new ObjectMapper();
        KafkaMessageDTO vagaStats = objectMapper.readValue(payload.value(), KafkaMessageDTO.class);

        vagaService.updateVagaStatus(vagaStats);

    }

}