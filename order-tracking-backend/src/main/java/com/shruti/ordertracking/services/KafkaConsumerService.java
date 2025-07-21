package com.shruti.ordertracking.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "order-events", groupId = "order_group")
    public void consumeOrderEvent(String message) {
        System.out.println("Received order event: " + message);
        // Here you can add logic to process the received order event
        // For example, you could update the order status in the database
        // or trigger some other business logic based on the event.

    }
}
