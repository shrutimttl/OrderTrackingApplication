package com.shruti.ordertracking.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.shruti.ordertracking.entities.Order;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "order-events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Order order) {
        try {
            String jsonOrder = objectMapper.writeValueAsString(order);
            kafkaTemplate.send(TOPIC, jsonOrder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
