package com.netflix_clone.userservice.components.configure.rabbit;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import com.netflix_clone.userservice.components.enums.Rabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration(value = "rabbit_configuration")
public class Config {
    private final String queueName = Rabbit.Queue.USER.getName();
    private final String topicExchangeName = Rabbit.Topic.USER.getName();


    @PostConstruct
    public void enabled(){
        ConfigMsg.msg("RabbitMQ");
    }
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
