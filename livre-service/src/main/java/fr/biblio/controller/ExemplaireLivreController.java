package fr.biblio.controller;

import fr.biblio.dao.ExemplaireLivreRepository;
import fr.biblio.entities.ExemplaireLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExemplaireLivreController {

    @Autowired
    private ExemplaireLivreRepository livreRepository;

    /**
     * Affiche la liste des exemplaires.
     */
    @GetMapping("/exemplaireLivre")
    public List<ExemplaireLivre> listeDesExemplaires() {
        List<ExemplaireLivre> exemplaireLivres = livreRepository.findAll();

        return exemplaireLivres;
    }

    /**
     * Affiche un exemplaire de par son ID.
     */
    @GetMapping("/exemplaireLivre/{id}")
    public ExemplaireLivre exemplaire(@PathVariable("id") long id) {

        ExemplaireLivre exemplaireLivre = livreRepository.findById(id).get();

        return exemplaireLivre;
    }

    /**
     * Affiche un exemplaire de par l'ID du livre.
     */
    @GetMapping("/exemplaireLivres/{livreId}")
    public List<ExemplaireLivre> exemplaireParLivre(@PathVariable("livreId") long id) {

        List<ExemplaireLivre> exemplaireLivre = livreRepository.findByLivreId(id);

        return exemplaireLivre;
    }

    /**
     * Affiche un exemplaire de par les IDs du livre et de la bibliothèque.
     */
    @GetMapping("/exemplaireLivres/{livreId}/{bibliothequeId}")
    public ExemplaireLivre exemplaireParLivreEtBiblio(@PathVariable("livreId") long livreId, @PathVariable("bibliothequeId") long bibliothequeId) {

        ExemplaireLivre exemplaireLivre = livreRepository.findExemplaireLivreByLivreIdAndBibliothequeId(livreId, bibliothequeId);

        return exemplaireLivre;
    }

    /**
     * Enregistre un exemplaire.
     */
    @PostMapping("/ajoutExemplaire")
    public ExemplaireLivre ajouterUnExemplaire(@RequestBody ExemplaireLivre exemplaireLivre) {
        return livreRepository.save(exemplaireLivre);
    }

    /**
     * Permet de modifier un exemplaire.
     */
    @PutMapping(value = "/modifExemplaire")
    public ExemplaireLivre modification(@RequestBody ExemplaireLivre exemplaireLivre) {
        return livreRepository.save(exemplaireLivre);
    }

}
