package fr.biblio.controller;

import fr.biblio.configuration.ApplicationConfiguration;
import fr.biblio.dao.LivreRepository;
import fr.biblio.entities.Livre;
import fr.biblio.exception.LivreIntrouvableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreController {

    @Autowired
    LivreRepository livreRepository;

    @Autowired
    ApplicationConfiguration appConfiguration;

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Affiche la liste des livres.
     */
    @GetMapping(value = "/livres")
    public List<Livre> listeDesLivres() {

        List<Livre> livres = livreRepository.findAll();

        List<Livre> listeLimitee = livres.subList(0, appConfiguration.getLimiteDeLivres());

        log.info("Récupération de la liste des livres");

        return listeLimitee;
    }

    /**
     * Permet d'effectuer une recherche par mot clé et de retourner le résultat.
     */
    @GetMapping(value = "/chercherLivre")
    public List<Livre> chercherLivre(@RequestParam(name="titre", defaultValue = "") String titre,
                                     @RequestParam(name="auteur", defaultValue = "") String auteur,
                                     @RequestParam(name="categorie", defaultValue = "") String categorie) {

        List<Livre> recherche = livreRepository.recherche("%" + titre + "%", "%" + auteur + "%",  "%" + categorie + "%");

        log.info("Recherche du livre : " + titre + " dont l'auteur est : " + auteur);

        return recherche;
    }

    /**
     * Affiche un livre.
     */
    @GetMapping(value = "/livres/{id}")
    public Livre afficherUnLivre(@PathVariable(name = "id") long id) throws LivreIntrouvableException {

        Livre livre = livreRepository.findById(id).orElse(null);

        if(livre == null) {
            throw new LivreIntrouvableException("Le livre avec l'id " + id + " est introuvable...");
        }

        return livre;
    }

    /**
     * Enregistre un livre.
     */
    @PostMapping(value = "/ajoutLivre")
    public Livre ajouterUnLivre(@RequestBody Livre livre) {
        return livreRepository.save(livre);
    }
}
