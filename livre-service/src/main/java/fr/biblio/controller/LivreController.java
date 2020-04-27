package fr.biblio.controller;

import fr.biblio.configuration.ApplicationConfiguration;
import fr.biblio.dao.LivreRepository;
import fr.biblio.entities.Livre;
import fr.biblio.exception.LivreIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivreController {

    @Autowired
    LivreRepository livreRepository;

    @Autowired
    ApplicationConfiguration appConfiguration;

    @GetMapping(value = "/livres")
    public List<Livre> listeDesLivres() {

        List<Livre> livres = livreRepository.findAll();

        List<Livre> listeLimitee = livres.subList(0, appConfiguration.getLimiteDeLivres());

        return listeLimitee;
    }

    @GetMapping(value = "/livres/{id}")
    public Livre afficherUnLivre(@PathVariable(name = "id") Long id) throws LivreIntrouvableException {

        Livre livre = livreRepository.findById(id).orElse(null);

        if(livre == null) {
            throw new LivreIntrouvableException("Le livre avec l'id " + id + " est introuvable...");
        }

        return livre;
    }
}
