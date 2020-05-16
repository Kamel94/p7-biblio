package fr.biblio.controller;

import fr.biblio.beans.*;
import fr.biblio.proxies.LivreProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private LivreProxy livreProxy;

    Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/accueil")
    public String accueil(Model model, Principal principal,
                          @RequestParam(name="titre", defaultValue = "") String titre,
                          @RequestParam(name="auteur", defaultValue = "") String auteur,
                          @RequestParam(name="categorie", defaultValue = "") String categorie) {


        List<Livre> livrePage = livreProxy.chercherLivre(titre, auteur, categorie);
        List<Bibliotheque> bibliotheques = livreProxy.listeDesBibliotheques();
        List<ExemplaireLivre> exemplaireLivres = livreProxy.listeDesExemplaires();
        if (principal != null) {
            Utilisateur utilisateur = livreProxy.email(principal.getName());
            model.addAttribute("utilisateur", utilisateur);
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(Long.valueOf(0));
            model.addAttribute("utilisateur", utilisateur);
        }

        log.info("ça passe" + "titre = " + titre + "auteur = " + auteur);
        log.info("La liste de recherche : " + livrePage);

        model.addAttribute("titre", titre);
        model.addAttribute("auteur", auteur);
        model.addAttribute("categorie", categorie);
        model.addAttribute("exemplaire", exemplaireLivres);
        model.addAttribute("biblios", bibliotheques);
        model.addAttribute("livres", livrePage);

        return "accueil";
    }

    @GetMapping("/detailsLivre/{id}")
    public String detailsLivre(@PathVariable("id") long id, Model model) {

        Livre livre = livreProxy.afficherUnLivre(id);
        List<ExemplaireLivre> exemplaireLivre = livreProxy.exemplaireParLivre(id);

        for (int i = 0; i < exemplaireLivre.size(); i++) {
            ExemplaireLivre exemplaire = livreProxy.exemplaireParLivreEtBiblio(exemplaireLivre.get(i).getLivreId(), exemplaireLivre.get(i).getBibliothequeId());
            model.addAttribute("exemplaire", exemplaire);
        }

        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireLivre);
        model.addAttribute("livre", livre);
        model.addAttribute("localDate", LocalDate.now());

        return "details";
    }

    @GetMapping(value = "/pretUtilisateur/{utilisateurId}/{statut}")
    public String pretUtilisateur(Model model,
                                  @PathVariable("utilisateurId") long utilisateurId,
                                  @PathVariable("statut") String statut) {

        List<Pret> prets = livreProxy.pretUtilisateur(utilisateurId, statut);
        Utilisateur utilisateur = livreProxy.utilisateur(utilisateurId);

        model.addAttribute("prets", prets);
        model.addAttribute("utilisateur", utilisateur);

        return "pretUtilisateur";
    }

    @GetMapping(value = "/detailsPret/{exemplaireId}")
    public String detailsPret(Model model, Principal principal,
                              @PathVariable("exemplaireId") long exemplaireId) {

        ExemplaireLivre exemplaireLivre = livreProxy.exemplaire(exemplaireId);
        Utilisateur utilisateur = livreProxy.email(principal.getName());

        model.addAttribute("exemplaire", exemplaireLivre);
        model.addAttribute("utilisateur", utilisateur);

        return "detailsPret";

    }

}
