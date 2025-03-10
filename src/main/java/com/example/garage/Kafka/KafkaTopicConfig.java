package com.example.garage.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic vehicleTopic() {
        // "vehicle-events" is the topic name.
        // "1" is the number of partitions.
        // "(short)1" is the replication factor.
        return new NewTopic("vehicle-events", 1, (short) 1);
    }
}
