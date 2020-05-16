package fr.biblio.proxies;

import fr.biblio.beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/authentification-service/listeUtilisateurs")
    List<Utilisateur> listeUtilisateurs();

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/utilisateurByEmail/{email}")
    Utilisateur email(@PathVariable("email") String email);

    @PostMapping(value = "/authentification-service/ajoutUtilisateur")
    Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur);

    @GetMapping(value = "/pret-service/pretUtilisateur/{utilisateurId}/{statut}")
    List<Pret> pretUtilisateur(@PathVariable("utilisateurId") long utilisateurId, @PathVariable("statut") String statut);

    @GetMapping(value = "/pret-service/prets/{id}")
    Pret pret(@PathVariable("id") long id);

}
