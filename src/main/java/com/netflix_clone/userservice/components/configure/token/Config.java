package com.netflix_clone.userservice.components.configure.token;

import com.netflix_clone.userservice.components.constants.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import tokenManager.TokenControl;

@Configuration(value = "token_control_configuration")
@DependsOn(value = {"constants"})
public class Config {

    @Bean
    public TokenControl tokenControl () {
        return new TokenControl(Constants.SALT, Constants.TOKEN_NAME, Constants.PROJECT_NAME);
    }
}
