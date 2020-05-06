package fr.biblio.controller;

import fr.biblio.dao.BibliothequeRepository;
import fr.biblio.entities.Bibliotheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/ajoutBibliotheque")
    public Bibliotheque ajouterUneBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }

}
