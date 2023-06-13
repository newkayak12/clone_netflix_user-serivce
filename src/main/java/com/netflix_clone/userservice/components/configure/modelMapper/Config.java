package com.netflix_clone.userservice.components.configure.modelMapper;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration(value = "modelMapper_configuration")
public class Config {

    @PostConstruct
    public void enabled(){
        ConfigMsg.msg("ModelMapper");
    }
    private ModelMapper modelMapper = new ModelMapper();

    private void strictStrategy() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    private void useReflection() {
        this.modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    @Bean
    public ModelMapper modelMapper() {
        this.strictStrategy();
        this.useReflection();
        return modelMapper;
    }
}
