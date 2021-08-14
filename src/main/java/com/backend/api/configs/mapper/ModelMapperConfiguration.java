package com.backend.api.configs.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
class ModelMapperConfiguration {
  @Bean
  ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();

    modelMapper.getConfiguration()
        .setAmbiguityIgnored(false)
        .setDeepCopyEnabled(true)
        .setFieldAccessLevel(PRIVATE)
        .setFieldMatchingEnabled(true);

    modelMapper.registerModule(new Jsr310Module());

    return modelMapper;
  }
}
