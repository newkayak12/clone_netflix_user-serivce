package com.netflix_clone.userservice.components.configure.eureka;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created on 2023-05-12
 * Project board-service
 */
@Configuration (value = "eureka_configuration")
@EnableEurekaClient
public class Config implements ConfigMsg {
    @PostConstruct
    public void enabled (){
        this.msg("Eureka");
    }
}
