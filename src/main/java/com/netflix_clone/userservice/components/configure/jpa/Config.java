package com.netflix_clone.userservice.components.configure.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@Configuration(value = "jpa_configuration")
@EnableJpaAuditing
public class Config {
}
