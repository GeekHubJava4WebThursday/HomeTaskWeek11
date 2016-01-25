package com.main;

import com.lang.LanguageDetector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com")
public class AppConfiguration {

    @Bean
    public ResourceLoader resourceLoader() {
        return new ResourceLoader();
    }

    @Bean
    public LanguageDetector languageDetector() {
        return new LanguageDetector();
    }

    @Bean
    public TextSource textSource() {
        return new TextSource();
    }

    @Bean
    public Dictionary dictionary() {
        return new Dictionary();
    }

    @Bean
    public Translator translator() {
        return new Translator();
    }
}
