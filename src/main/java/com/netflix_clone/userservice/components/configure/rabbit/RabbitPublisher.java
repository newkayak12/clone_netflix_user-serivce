package com.netflix_clone.userservice.components.configure.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Objects;


//@Configuration(value = "rabbit_configuration")
@Component(value = "rabbit_publisher")
@DependsOn(value = "objectMapper")
@RequiredArgsConstructor
public class RabbitPublisher {

    private final RabbitTemplate rabbit;
    private final ObjectMapper objectMapper;

//    @Autowired
//    public RabbitPublisher(RabbitTemplate rabbit){
//        this.rabbit = rabbit;
////        this.rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
//    }

    public void sendTopic(String topicName, String routingKey, Object message) {
        System.out.println("SEND MQ : " + topicName +" : " + routingKey);
        String messageJson = null;
        try {
            messageJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbit.convertAndSend(topicName, routingKey, messageJson);
    }

}
