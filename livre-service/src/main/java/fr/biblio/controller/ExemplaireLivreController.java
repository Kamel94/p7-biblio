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

    @GetMapping("/exemplaireLivre")
    public List<ExemplaireLivre> listeDesExemplaires() {
        List<ExemplaireLivre> exemplaireLivres = livreRepository.findAll();

        return exemplaireLivres;
    }

    @GetMapping("/exemplaireLivre/{id}")
    public ExemplaireLivre exemplaire(@PathVariable("id") long id) {

        ExemplaireLivre exemplaireLivre = livreRepository.findById(id).get();

        return exemplaireLivre;
    }

    @GetMapping("/exemplaireLivres/{livreId}")
    public List<ExemplaireLivre> exemplaireParLivre(@PathVariable("livreId") long id) {

        List<ExemplaireLivre> exemplaireLivre = livreRepository.findByLivreId(id);

        return exemplaireLivre;
    }

    @GetMapping("/exemplaireLivres/{livreId}/{bibliothequeId}")
    public ExemplaireLivre exemplaireParLivreEtBiblio(@PathVariable("livreId") long livreId, @PathVariable("bibliothequeId") long bibliothequeId) {

        ExemplaireLivre exemplaireLivre = livreRepository.findExemplaireLivreByLivreIdAndBibliothequeId(livreId, bibliothequeId);

        return exemplaireLivre;
    }

    @PostMapping("/ajoutExemplaire")
    public ExemplaireLivre ajouterUnExemplaire(@RequestBody ExemplaireLivre exemplaireLivre) {
        return livreRepository.save(exemplaireLivre);
    }

    @PutMapping(value = "/modifExemplaire")
    public ExemplaireLivre modification(@RequestBody ExemplaireLivre exemplaireLivre) {
        return livreRepository.save(exemplaireLivre);
    }

}
