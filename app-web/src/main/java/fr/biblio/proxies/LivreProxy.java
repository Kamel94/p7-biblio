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
    Livre getLivre(@PathVariable(name = "id") long id);

    @GetMapping(value = "/livre-service/chercherLivre")
    List<Livre> chercherLivreParCriteres(@RequestParam(name="titre", defaultValue = "") String titre,
                              @RequestParam(name="auteur", defaultValue = "") String auteur,
                              @RequestParam(name="categorie", defaultValue = "") String categorie);

    @GetMapping(value = "/livre-service/exemplaireLivres/{livreId}")
    List<ExemplaireLivre> getExemplairesWithLivreId(@PathVariable("livreId") long id);

    @GetMapping(value = "/authentification-service/listeUtilisateurs")
    List<Utilisateur> listeUtilisateurs();

    @GetMapping(value = "/authentification-service/utilisateur/{id}")
    Utilisateur getUtilisateur(@PathVariable("id") long id);

    @GetMapping(value = "/authentification-service/utilisateurByEmail/{email}")
    Utilisateur getUtilisateurWithEmail(@PathVariable("email") String email);

    @PostMapping(value = "/authentification-service/ajoutUtilisateur")
    Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur);

    @RequestMapping(value = "/pret-service/prets")
    List<Pret> listeDesPrets();

    @GetMapping(value = "/pret-service/pretUtilisateur/{utilisateurId}")
    List<Pret> getPretsWithUtilisateurId(@PathVariable("utilisateurId") long utilisateurId);

    @PostMapping(value = "/pret-service/prolongation/{pretId}")
    Pret prolongerPret(@PathVariable("pretId") long pretId);

}
