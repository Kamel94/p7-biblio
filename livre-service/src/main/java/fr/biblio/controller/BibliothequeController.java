package fr.biblio.controller;

import fr.biblio.dao.BibliothequeRepository;
import fr.biblio.entities.Bibliotheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BibliothequeController {

    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    @GetMapping(value = "/listeBibliotheques")
    public List<Bibliotheque> listeDesBibliotheques() {
        List<Bibliotheque> bibliotheques = bibliothequeRepository.findAll();
        return bibliotheques;
    }

    @GetMapping(value = "/listeBibliotheques/{id}")
    public Bibliotheque bibliotheque(@PathVariable("id") long id) {
        return bibliothequeRepository.findById(id).orElse(null);
    }

}
