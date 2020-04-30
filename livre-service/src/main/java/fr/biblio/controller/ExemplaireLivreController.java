package fr.biblio.controller;

import fr.biblio.dao.ExemplaireLivreRepository;
import fr.biblio.entities.ExemplaireLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ExemplaireLivre> exemplaireParLivre(@PathVariable("id") long id) {

        List<ExemplaireLivre> exemplaireLivre = livreRepository.findByLivreId(id);

        return exemplaireLivre;
    }

    @GetMapping("/exemplaireLivre/{livreId}/{bibliothequeId}")
    public ExemplaireLivre exemplaireParLivreEtBiblio(@PathVariable("livreId") long livreId, @PathVariable("bibliothequeId") long bibliothequeId) {

        ExemplaireLivre exemplaireLivre = livreRepository.findExemplaireLivreByLivreIdAndBibliothequeId(livreId, bibliothequeId);

        return exemplaireLivre;
    }

    @GetMapping("/test/{id}")
    public List<ExemplaireLivre> test(@PathVariable("id") long id) {
        return livreRepository.findByLivreId(id);
    }

}
