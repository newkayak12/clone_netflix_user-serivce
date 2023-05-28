package com.netflix_clone.userservice.configure.rabbit;

import com.netflix_clone.userservice.enums.Rabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration(value = "rabbit_configuration")
public class Config {
    private final String queueName = Rabbit.Queue.USER.getName();
    private final String topicExchangeName = Rabbit.Topic.USER.getName();

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

//    @Bean
//    public Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("profile.*");
//    }

}
