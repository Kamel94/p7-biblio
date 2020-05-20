package fr.biblio.configuration;

import fr.biblio.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public CustomErrorDecoder decoder() {
        return new CustomErrorDecoder();
    }

}
