package com.lamppit.pedido.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.mappers.ModelMapper;

@Configuration
public class ConfigModelMapper {
    @Bean
    public ModelMapper obterModelMapper(){
        return new ModelMapper();
    }
}
