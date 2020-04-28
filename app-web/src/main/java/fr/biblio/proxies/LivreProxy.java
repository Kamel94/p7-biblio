package fr.biblio.proxies;

import fr.biblio.beans.Livre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface LivreProxy {

    @GetMapping(value = "/livre-service/livres")
    List<Livre> listeDesLivres();

}
