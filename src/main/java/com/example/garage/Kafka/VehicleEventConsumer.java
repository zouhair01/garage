package com.example.garage.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VehicleEventConsumer {
   /* @KafkaListener(topics = "vehicle-events", groupId = "garage-group")
    public void consumeVehicleEvent(String message) {
        System.out.println("Received vehicle event: " + message);
        // Process the event (e.g., logging, further actions, etc.)
    }*/
}
