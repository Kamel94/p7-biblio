package fr.biblio.controller;

import fr.biblio.beans.ExemplaireLivre;
import fr.biblio.beans.Livre;
import fr.biblio.proxies.LivreProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private LivreProxy livreProxy;

    @RequestMapping("/accueil")
    public String accueil(Model model) {

        List<Livre> livres = livreProxy.listeDesLivres();

        model.addAttribute("livres", livres);

        return "accueil";
    }

    @GetMapping("/detailsLivre/{id}")
    public String detailsLivre(@PathVariable("id") long id, Model model) {

        Livre livre = livreProxy.afficherUnLivre(id);
        ExemplaireLivre exemplaireLivre = livreProxy.exemplaireParLivre(livre.getId());

        model.addAttribute("livre", livre);
        model.addAttribute("exemplaire", exemplaireLivre);

        return "details";
    }

}
