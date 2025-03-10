package com.example.garage.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VehicleEventConsumer {
    @KafkaListener(topics = "vehicle-events", groupId = "vehicle-group")
    public void consumeVehicleEvent(String message) {
        // Here, you process the message.
        // For example, log it, update a cache, trigger a notification, etc.
        System.out.println("Received Vehicle Event: " + message);
    }
}
