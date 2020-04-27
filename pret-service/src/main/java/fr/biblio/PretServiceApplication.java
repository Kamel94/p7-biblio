package fr.biblio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.biblio")
public class PretServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PretServiceApplication.class, args);
	}

}
