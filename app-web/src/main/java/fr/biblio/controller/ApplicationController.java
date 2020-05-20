package fr.biblio.controller;

import fr.biblio.beans.*;
import fr.biblio.proxies.LivreProxy;
import fr.biblio.service.CompteService;
import fr.biblio.service.DateFormat;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private LivreProxy livreProxy;

    @Autowired
    private DateFormat dateFormat;

    @Autowired
    private CompteService compteService;

    Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/accueil")
    public String accueil(Model model, Principal principal,
                          @RequestParam(name="titre", defaultValue = "") String titre,
                          @RequestParam(name="auteur", defaultValue = "") String auteur,
                          @RequestParam(name="categorie", defaultValue = "") String categorie) {


        List<Livre> livres = livreProxy.chercherLivre(titre, auteur, categorie);
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

        model.addAttribute("titre", titre);
        model.addAttribute("auteur", auteur);
        model.addAttribute("categorie", categorie);
        model.addAttribute("exemplaire", exemplaireLivres);
        model.addAttribute("biblios", bibliotheques);
        model.addAttribute("livres", livres);

        return "accueil";
    }

    @GetMapping("/detailsLivre/{id}")
    public String detailsLivre(@PathVariable("id") long id, Model model, Principal principal) {

        Livre livre = livreProxy.afficherUnLivre(id);
        List<ExemplaireLivre> exemplaireLivre = livreProxy.exemplaireParLivre(id);
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

        if (principal == null){
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(Long.valueOf(0));
            model.addAttribute("utilisateur", utilisateur);
        } else {
            Utilisateur utilisateur = livreProxy.email(principal.getName());
            model.addAttribute("utilisateur", utilisateur);
        }

        for (int i = 0; i < exemplaireLivre.size(); i++) {
            ExemplaireLivre exemplaire = livreProxy.exemplaireParLivreEtBiblio(exemplaireLivre.get(i).getLivreId(), exemplaireLivre.get(i).getBibliothequeId());
            model.addAttribute("exemplaire", exemplaire);
        }

        String date = simpleDateFormat.format(livre.getEdition());
        livre.setEditionString(date);

        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireLivre);
        model.addAttribute("livre", livre);
        model.addAttribute("localDate", LocalDate.now());

        return "details";
    }

    @GetMapping(value = "/usager/pretUtilisateur/{utilisateurId}")
    public String pretUtilisateur(Model model,
                                  @PathVariable("utilisateurId") long utilisateurId) {

        List<Pret> prets = livreProxy.pretUtilisateur(utilisateurId);

        Utilisateur utilisateur = livreProxy.utilisateur(utilisateurId);

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

    @GetMapping(value = "/personnel/gestionPrets")
    public String gestionPrets(Principal principal, Model model) {
        List<Pret> prets = livreProxy.listeDesPrets();
        if (principal != null) {
            Utilisateur u = livreProxy.email(principal.getName());
            model.addAttribute("utilisateur", u);
        } else {
            Utilisateur u = new Utilisateur();
            u.setId(Long.valueOf(0));
            model.addAttribute("utilisateur", u);
        }

        try {
            for (Pret pret : prets) {
                ExemplaireLivre exemplaireLivre = livreProxy.exemplaire(pret.getExemplaireId());
                Livre livre = livreProxy.afficherUnLivre(exemplaireLivre.getLivreId());
                Bibliotheque bibliotheque = livreProxy.bibliotheque(exemplaireLivre.getBibliothequeId());
                Utilisateur utilisateur = livreProxy.utilisateur(pret.getUtilisateurId());
                String date = dateFormat.dateRetour(pret.getId());

                pret.setGenreId(utilisateur.getGenreId());
                pret.setUtilisateurNom(utilisateur.getNom());
                pret.setUtilisateurPrenom(utilisateur.getPrenom());
                pret.setTitreLivre(livre.getTitre());
                pret.setNomBiblio(bibliotheque.getNom());
                pret.setDateRetourString(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("prets", prets);

        return "gestionPrets";
    }

    @GetMapping(value = "/livreRendu/{pretId}")
    public String livreRendu(@PathVariable("pretId") long pretId) {

        livreProxy.livreRendu(pretId);

        return "redirect:/personnel/gestionPrets";
    }

    @GetMapping(value = "/prolongation/{pretId}/{utilisateurId}")
    public String prolongation(@PathVariable("pretId") long pretId,
                               @PathVariable("utilisateurId") long utilisateurId) {

        Pret prolongation = livreProxy.prolongation(pretId);

        return "redirect:/usager/pretUtilisateur/{utilisateurId}";
    }

    @GetMapping(value = "/usager/ajoutPret/{livreId}/{exemplaireId}")
    public String ajoutPret(@PathVariable("livreId") long livreId,
                            @PathVariable("exemplaireId") long exemplaireId,
                            Principal principal, Pret pret) {

        if (principal != null) {
            Utilisateur utilisateur = livreProxy.email(principal.getName());
            pret.setUtilisateurId(utilisateur.getId());
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(Long.valueOf(0));
        }
        ExemplaireLivre exemplaireLivre = livreProxy.exemplaire(exemplaireId);

        exemplaireLivre.setNombreExemplaire(exemplaireLivre.getNombreExemplaire() - 1);

        if (exemplaireLivre.getNombreExemplaire() == 0) {
            exemplaireLivre.setDisponibilite(false);
        }

        livreProxy.modification(exemplaireLivre);

        try {
            GregorianCalendar date = new GregorianCalendar();

            pret.setDatePret(new Date());

            date.setTime(pret.getDatePret());
            date.add(GregorianCalendar.DAY_OF_YEAR, +28);

            pret.setDateRetour(date.getTime());
            pret.setProlongation(0);
            pret.setExemplaireId(exemplaireLivre.getId());
            pret.setStatut("PRET");

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("pret = " + pret);
        livreProxy.ajoutPret(pret);

        return "redirect:/detailsLivre/{livreId}";
    }

    @GetMapping(value = "/inscription")
    public String inscription(Model model, Principal principal) {

        LocalDateTime dateTime = LocalDateTime.now();

        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("localDate", dateTime);

        if(principal != null) {
            Utilisateur utilisateur = livreProxy.email(principal.getName());
            model.addAttribute("u", utilisateur);
        } else {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setStatut("VISITEUR");
            model.addAttribute("u", utilisateur);
        }

        return "inscription";
    }

    /*
     * méthode qui permet d'enregistrer l'inscription ou
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
