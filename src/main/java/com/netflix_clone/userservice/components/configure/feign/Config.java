package com.netflix_clone.userservice.components.configure.feign;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created on 2023-05-12
 * Project user-service
 */
@Configuration(value = "feign_configuration")
@EnableFeignClients
public class Config implements ConfigMsg {
    @PostConstruct
    public void enabled (){
        msg("Feign");
    }

    @Bean
    Logger.Level loggerLevel () {
        return Logger.Level.FULL;
    }
}
