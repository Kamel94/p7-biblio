package fr.biblio.controller;

import fr.biblio.beans.Bibliotheque;
import fr.biblio.beans.ExemplaireLivre;
import fr.biblio.beans.Livre;
import fr.biblio.beans.Pret;
import fr.biblio.proxies.LivreProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaire", exemplaireLivre);/**/
        model.addAttribute("livre", livre);
        model.addAttribute("localDate", LocalDate.now());

        return "details";
    }

    @PostMapping(value = "/ajoutPret/{id}")
    public String ajoutPret(Model model, @Valid Pret pret,
                            BindingResult bindingResult,
                            @PathVariable("id") long id) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            return "redirect:/detailsLivre/{id}";
        }

        LocalDateTime dateTime = LocalDateTime.now();
       // livreProxy.ajoutPret(new Pret(null, null, null, "PRET", true, id, Long.valueOf(1), Long.valueOf(3)));
        //model.addAttribute("localDate", Timestamp.valueOf(dateTime));
        model.addAttribute("pret", pret);

        return "ajoutPret";
    }

    @GetMapping("/bibliotheques/{id}/{livreId}")
    public String exemplaireLivreParBiblio(@PathVariable("id") long id, @PathVariable("livreId") long livreId,Model model) {

       // Livre livre = livreProxy.afficherUnLivre(id);
        /*ExemplaireLivre livre = livreProxy.exemplaireParLivre(livreId);
        Bibliotheque bibliotheque = livreProxy.bibliotheque(livre.getBibliothequeId());

        model.addAttribute("biblio", bibliotheque);
        model.addAttribute("exemplaire", livre);*/

        return "ajoutPret";
    }

}
