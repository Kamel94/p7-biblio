package fr.biblio.proxies;

import fr.biblio.beans.ExemplaireLivre;
import fr.biblio.beans.Livre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface LivreProxy {

    @GetMapping(value = "/livre-service/livres")
    List<Livre> listeDesLivres();

    @GetMapping(value = "/livre-service/livres/{id}")
    Livre afficherUnLivre(@PathVariable(name = "id") long id);

    @GetMapping(value = "/livre-service/exemplaireLivre/{id}")
    ExemplaireLivre exemplaireParLivre(@PathVariable("id") long id);

}
