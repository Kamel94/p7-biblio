package fr.biblio.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import fr.biblio.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor(){
        return  new BasicAuthRequestInterceptor("utilisateur", "mdp");
    }

    @Bean
    public CustomErrorDecoder decoder() {
        return new CustomErrorDecoder();
    }

}
