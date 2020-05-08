package fr.biblio;

import fr.biblio.controller.SimpleEmailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@EnableFeignClients("fr.biblio")
@EnableDiscoveryClient
public class AppBatchApplication {

    @Autowired
    SimpleEmailController simpleEmailController;

    public static void main(String[] args) {
        SpringApplication.run(AppBatchApplication.class, args);
    }

    @Bean
    public ResponseEntity envoieMail(){
        return simpleEmailController.simpleEmail();
    }

}
