package com.netflix_clone.userservice.components.configure.emailSender;

import com.github.newkayak12.config.SimpleEmailSender;
import com.github.newkayak12.enums.Vendor;
import com.netflix_clone.userservice.components.configure.ConfigMsg;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "emailSender_configuration")
@EnableEurekaClient
public class Config implements ConfigMsg {

    @Bean
    public SimpleEmailSender simpleEmailSender () {
        return new SimpleEmailSender(Vendor.GMAIL, "/email/", null, null);
    }
}
