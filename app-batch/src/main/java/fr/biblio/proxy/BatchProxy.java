package fr.biblio.proxy;

import fr.biblio.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface BatchProxy {

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur getUtilisateur(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/livres/{id}")
    Livre getLivre(@PathVariable("id") long id);

    @GetMapping("/livre-service/exemplaireLivre/{id}")
    ExemplaireLivre getExemplaire(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/listeBibliotheques/{id}")
    Bibliotheque getBibliotheque(@PathVariable("id") long id);

    @GetMapping(value = "/pret-service/dateRetourPassee")
    List<Pret> getPretsFinished();

}
