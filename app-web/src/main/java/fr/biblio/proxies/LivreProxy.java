package fr.biblio.proxies;

import fr.biblio.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/livre-service/listeBibliotheques")
    List<Bibliotheque> listeDesBibliotheques();

    @GetMapping(value = "/livre-service/listeBibliotheques/{id}")
    Bibliotheque bibliotheque(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/listeUtilisateurs")
    List<Utilisateur> listeUtilisateurs();

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/utilisateurByEmail/{email}")
    Utilisateur email(@PathVariable("email") String email);

    @PostMapping(value = "/authentification-service/ajoutUtilisateur")
    Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur);

    @RequestMapping(value = "/pret-service/prets")
    List<Pret> listeDesPrets();

    @GetMapping(value = "/pret-service/pretUtilisateur/{utilisateurId}")
    List<Pret> pretUtilisateur(@PathVariable("utilisateurId") long utilisateurId);

    @GetMapping(value = "/datePassee")
    List<Pret> datePassee();

    @PostMapping(value = "/pret-service/livreRendu/{pretId}")
    Pret livreRendu(@PathVariable("pretId") long pretId);

    @PostMapping(value = "/pret-service/prolongation/{pretId}")
    Pret prolongation(@PathVariable("pretId") long pretId);

    @GetMapping(value = "/pret-service/prets/{id}")
    Pret pret(@PathVariable("id") long id);

    @PostMapping(value = "/pret-service/ajoutPret")
    Pret ajoutPret(@RequestBody Pret pret);

    @PutMapping(value = "/livre-service/modifExemplaire")
    ExemplaireLivre modification(@RequestBody ExemplaireLivre exemplaireLivre);

}
