package com.netflix_clone.userservice.configure.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


//@Configuration(value = "rabbit_configuration")
@Component(value = "rabbit_publisher")
@RequiredArgsConstructor
public class RabbitPublisher {

    private final RabbitTemplate rabbit;
    private final ObjectMapper objectMapper;

    public void send(String exchange, String routingKey, Object message) {
        try {
            String convertedMessage = objectMapper.writeValueAsString(message);
            rabbit.convertAndSend(exchange, routingKey, convertedMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
