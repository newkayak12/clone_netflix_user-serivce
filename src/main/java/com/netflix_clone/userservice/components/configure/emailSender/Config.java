package com.netflix_clone.userservice.components.configure.emailSender;

import com.github.newkayak12.config.SimpleEmailSender;
import com.github.newkayak12.enums.Vendor;
import com.netflix_clone.userservice.components.configure.ConfigMsg;
import com.netflix_clone.userservice.components.constants.Constants;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@Configuration(value = "emailSender_configuration")
@DependsOn(value = "constants")
public class Config{

    @PostConstruct
    public void enabled(){
        ConfigMsg.msg("EmailSender");
    }

    @Bean
    public SimpleEmailSender simpleEmailSender () {
        return new SimpleEmailSender(Vendor.GMAIL, "email/", Constants.EMAIL, Constants.EMAIL_KEY);
    }
}
