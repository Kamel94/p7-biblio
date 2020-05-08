package fr.biblio.proxies;

import fr.biblio.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface LivreProxy {

    @GetMapping(value = "/livre-service/livres")
    List<Livre> listeDesLivres();

    @GetMapping(value = "/livre-service/livres/{id}")
    Livre afficherUnLivre(@PathVariable(name = "id") long id);

    @GetMapping(value = "/livre-service/chercherLivre")
    List<Livre> chercherLivre(@RequestParam(name="titre", defaultValue = "") String titre,
                              @RequestParam(name="auteur", defaultValue = "") String auteur,
                              @RequestParam(name="categorie", defaultValue = "") String categorie);

    @GetMapping(value = "/livre-service/exemplaireLivres/{livreId}")
    List<ExemplaireLivre> exemplaireParLivre(@PathVariable("livreId") long id);

    @GetMapping("/livre-service/exemplaireLivre")
    List<ExemplaireLivre> listeDesExemplaires();

    @GetMapping("/livre-service/exemplaireLivre/{id}")
    ExemplaireLivre exemplaire(@PathVariable("id") long id);

    @GetMapping("/livre-service/exemplaireLivres/{livreId}/{bibliothequeId}")
    ExemplaireLivre exemplaireParLivreEtBiblio(@PathVariable("livreId") long livreId, @PathVariable("bibliothequeId") long bibliothequeId);

    @GetMapping(value = "/livre-service/listeBibliotheques")
    List<Bibliotheque> listeDesBibliotheques();

    @GetMapping(value = "/livre-service/listeBibliotheques/{id}")
    Bibliotheque bibliotheque(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id);

    @PostMapping(value = "/pret-service/ajoutPret/{id}")
    Pret ajoutPret(Pret pret);

}
