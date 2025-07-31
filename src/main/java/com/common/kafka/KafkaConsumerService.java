package com.common.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic", groupId = "my-spring-boot-app-group")
    public void listen(String message) {
        System.out.println("Received message from my-topic: " + message);
    }
}
