package com.netflix_clone.userservice.components.configure.jpa;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@Configuration(value = "jpa_configuration")
@EnableJpaAuditing
public class Config implements ConfigMsg {
    @PostConstruct
    public void msg(){
        this.msg("JPA");
    }
}
