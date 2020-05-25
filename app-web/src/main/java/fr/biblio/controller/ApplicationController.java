package fr.biblio.controller;

import fr.biblio.beans.*;
import fr.biblio.proxies.LivreProxy;
import fr.biblio.service.CompteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private LivreProxy livreProxy;

    @Autowired
    private CompteService compteService;

    Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/accueil")
    public String accueil(Model model, Principal principal,
                          @RequestParam(name="titre", defaultValue = "") String titre,
                          @RequestParam(name="auteur", defaultValue = "") String auteur,
                          @RequestParam(name="categorie", defaultValue = "") String categorie) {


        List<Livre> livres = livreProxy.chercherLivreParCriteres(titre, auteur, categorie);

        if (principal != null) {
            Utilisateur utilisateur = livreProxy.getUtilisateurWithEmail(principal.getName());
            model.addAttribute("utilisateur", utilisateur);
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(Long.valueOf(0));
            model.addAttribute("utilisateur", utilisateur);
        }

        model.addAttribute("titre", titre);
        model.addAttribute("auteur", auteur);
        model.addAttribute("categorie", categorie);
        model.addAttribute("livres", livres);

        return "accueil";
    }

    /**
     * Affiche le détail d'un livre.
     */
    @GetMapping("/detailsLivre/{id}")
    public String detailsLivre(@PathVariable("id") long id, Model model, Principal principal) {

        Livre livre = livreProxy.getLivre(id);
        List<ExemplaireLivre> exemplaireLivre = livreProxy.getExemplairesWithLivreId(id);
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

        if (principal == null){
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(Long.valueOf(0));
            model.addAttribute("utilisateur", utilisateur);
        } else {
            Utilisateur utilisateur = livreProxy.getUtilisateurWithEmail(principal.getName());
            model.addAttribute("utilisateur", utilisateur);
        }

        String date = simpleDateFormat.format(livre.getEdition());
        livre.setEditionString(date);

        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireLivre);
        model.addAttribute("livre", livre);
        model.addAttribute("localDate", LocalDate.now());

        return "details";
    }

    /**
     * Affiche la liste des prêts en cours de l'utilisateur connecté.
     */
    @GetMapping(value = "/usager/pretUtilisateur/{utilisateurId}")
    public String pretUtilisateur(Model model,
                                  @PathVariable("utilisateurId") long utilisateurId) {

        List<Pret> prets = livreProxy.getPretsWithUtilisateurId(utilisateurId);

        Utilisateur utilisateur = livreProxy.getUtilisateur(utilisateurId);

        String formatDate = "dd/MM/yyyy";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

        try {
            for (Pret pret : prets) {
                String date = simpleDateFormat.format(pret.getDateRetour());
                pret.setDateRetourString(date);
                model.addAttribute("date", date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("prets", prets);
        model.addAttribute("utilisateur", utilisateur);

        return "pretUtilisateur";
    }

    /**
     * Permet aux usagers de prolonger un emprunt.
     */
    @GetMapping(value = "/prolongation/{pretId}/{utilisateurId}")
    public String prolongation(@PathVariable("pretId") long pretId,
                               @PathVariable("utilisateurId") long utilisateurId) {

        Pret prolongation = livreProxy.prolongerPret(pretId);

        return "redirect:/usager/pretUtilisateur/{utilisateurId}";
    }

    /**
     * Affiche le formulaire d'inscription.
     */
    @GetMapping(value = "/inscription")
    public String inscription(Model model, Principal principal) {

        LocalDateTime dateTime = LocalDateTime.now();

        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("localDate", dateTime);

        if(principal != null) {
            Utilisateur utilisateur = livreProxy.getUtilisateurWithEmail(principal.getName());
            model.addAttribute("u", utilisateur);
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setStatut("VISITEUR");
            model.addAttribute("u", utilisateur);
        }

        return "inscription";
    }

    /**
     * Méthode qui permet d'enregistrer l'inscription ou
     * de renvoyer vers le formulaire d'inscription en cas d'erreur.
     */
    @RequestMapping(value="/enregistrer", method=RequestMethod.POST)
    public String enregistrer(Model model, @Valid Utilisateur utilisateur, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.warn("Erreur lors de l'inscription " + bindingResult.getFieldError());
            return "redirect:/inscription";
        }

        log.info("Utilisateur ajouté");

        compteService.saveUser(utilisateur);
        return "confirmation";
    }
}
