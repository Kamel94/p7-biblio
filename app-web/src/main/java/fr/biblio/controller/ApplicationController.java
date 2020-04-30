package fr.biblio.controller;

import fr.biblio.beans.Bibliotheque;
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
        List<Bibliotheque> bibliotheques = livreProxy.listeDesBibliotheques();

        model.addAttribute("biblios", bibliotheques);
        model.addAttribute("livres", livres);

        return "accueil";
    }

    @GetMapping("/detailsLivre/{id}")
    public String detailsLivre(@PathVariable("id") long id, Model model) {

        Livre livre = livreProxy.afficherUnLivre(id);
        List<ExemplaireLivre> exemplaireLivre = livreProxy.exemplaireParLivre(id);
      //  Bibliotheque bibliotheque = livreProxy.bibliotheque(exemplaireLivre.getBibliothequeId());

       // model.addAttribute("biblio", bibliotheque);
        model.addAttribute("exemplaire", exemplaireLivre);/**/
        model.addAttribute("livre", livre);

        return "details";
    }

    @GetMapping("/bibliotheques/{id}/{livreId}")
    public String exemplaireLivreParBiblio(@PathVariable("id") long id, @PathVariable("livreId") long livreId,Model model) {

       // Livre livre = livreProxy.afficherUnLivre(id);
        /*ExemplaireLivre livre = livreProxy.exemplaireParLivre(livreId);
        Bibliotheque bibliotheque = livreProxy.bibliotheque(livre.getBibliothequeId());

        model.addAttribute("biblio", bibliotheque);
        model.addAttribute("exemplaire", livre);*/

        return "exemplaire";
    }

}
