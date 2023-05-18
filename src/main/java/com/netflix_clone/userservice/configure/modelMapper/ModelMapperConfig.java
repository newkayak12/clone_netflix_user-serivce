package com.netflix_clone.userservice.configure.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration(value = "modelMapperConfig")
public class ModelMapperConfig {
    private ModelMapper modelMapper = new ModelMapper();

    private void strictStrategy() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Bean
    public ModelMapper modelMapper() {
        this.strictStrategy();
        return modelMapper;
    }
}
