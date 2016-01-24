package com.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com")
public class AppConfiguration {

    @Bean
    Translator translator() {
        return new Translator();
    }

}
