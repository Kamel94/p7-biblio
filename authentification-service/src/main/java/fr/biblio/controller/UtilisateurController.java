package fr.biblio.controller;

import fr.biblio.dao.UtilisateurRepository;
import fr.biblio.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping(value = "/listeUtilisateurs")
    public List<Utilisateur> listeUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @GetMapping(value = "/utilisateur/{id}")
    public Utilisateur utilisateur(@PathVariable("id") long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/ajoutUtilisateur")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {

        String passEncoder = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(passEncoder);

        return utilisateurRepository.save(utilisateur);
    }

}
