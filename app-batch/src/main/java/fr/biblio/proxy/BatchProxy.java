package fr.biblio.proxy;

import fr.biblio.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface BatchProxy {

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/listeUtilisateurs/{id}")
    List<Utilisateur> utilisateurs(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/listeLivres/{id}")
    List<Livre> livres(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/exemplaireLivres/{id}")
    List<ExemplaireLivre> exemplaireLivres(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/bibliotheques/{id}")
    List<Bibliotheque> bibliotheques(@PathVariable("id") long id);

    @GetMapping(value = "/pret-service/prets/{id}")
    Pret pret(@PathVariable("id") long id);

    /*@GetMapping(value = "/pret-service/pretStatut/{statut}")
    Pret statut(@PathVariable("statut") String statut);*/

    @GetMapping(value = "/livre-service/livres/{id}")
    Livre afficherUnLivre(@PathVariable("id") long id);

    @GetMapping("/livre-service/exemplaireLivre/{id}")
    ExemplaireLivre exemplaire(@PathVariable("id") long id);

    @GetMapping(value = "/livre-service/listeBibliotheques/{id}")
    Bibliotheque bibliotheque(@PathVariable("id") long id);

    @GetMapping(value = "/pret-service/retardRetour")
    List<Pret> retardRetour();

}
