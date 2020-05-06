package fr.biblio.controller;

import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Livre;
import fr.biblio.entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class PretController {

    @Autowired
    PretRepository pretRepository;

    @RequestMapping(value = "/prets")
    public List<Pret> listeDesPrets() {

        List<Pret> prets = pretRepository.findAll();

        return prets;
    }

    @PostMapping(value = "/ajoutPret")
    public Pret ajoutPret(@RequestBody Pret pret) {
        return pretRepository.save(pret);
    }

   /* @RequestMapping("/")
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
    }*/

}
