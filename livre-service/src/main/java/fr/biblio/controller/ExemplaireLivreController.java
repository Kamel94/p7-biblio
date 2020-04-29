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
    public ExemplaireLivre exemplaireParLivre(@PathVariable("id") long id) {

        ExemplaireLivre exemplaireLivre = livreRepository.findExemplaireLivreByLivreId(id);

       // ExemplaireLivre exemplaireLivre = livreRepository.findById(id).orElse(null);

        return exemplaireLivre;
    }

}
