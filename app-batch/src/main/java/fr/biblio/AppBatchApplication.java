package fr.biblio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.biblio")
@EnableDiscoveryClient
public class AppBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBatchApplication.class, args);
    }

}
