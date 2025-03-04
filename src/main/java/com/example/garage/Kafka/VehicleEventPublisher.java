package com.example.garage.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VehicleEventPublisher {
   /* private final KafkaTemplate<String, String> kafkaTemplate;

    public VehicleEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishVehicleEvent(String message) {
        kafkaTemplate.send("vehicle-events", message);
    }*/
}
