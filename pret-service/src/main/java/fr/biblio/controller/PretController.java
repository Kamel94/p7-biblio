package fr.biblio.controller;

import fr.biblio.beans.LivreBean;
import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Pret;
import fr.biblio.proxies.LivreProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class PretController {

    @Autowired
    LivreProxy livreProxy;

    @Autowired
    PretRepository pretRepository;

    @GetMapping(value = "/prets")
    public List<Pret> listeDesPrets() {

        List<Pret> prets = pretRepository.findAll();

        return prets;
    }

    @RequestMapping("/")
    public String accueil(Model model) {

        List<LivreBean> livres =  livreProxy.listeDesLivres();

       model.addAttribute("livres", livres);

        return "test";
    }

    @PostMapping("/ajout")
    public String ajout(Model model) {

        List<LivreBean> livreBeans = livreProxy.listeDesLivres();

        livreBeans.forEach(l -> {
            pretRepository.save(new Pret(null, new Date(), new Date(), null, false, l.getId(), null, null, null, null, null));
        });

        return "ajout";
    }

}
