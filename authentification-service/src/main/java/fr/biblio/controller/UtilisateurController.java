package fr.biblio.controller;

import fr.biblio.dao.UtilisateurRepository;
import fr.biblio.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping(value = "/listeUtilisateurs")
    public List<Utilisateur> utilisateurs() {
        return utilisateurRepository.findAll();
    }

}
