package fr.biblio.controller;

import fr.biblio.beans.Bibliotheque;
import fr.biblio.beans.ExemplaireLivre;
import fr.biblio.beans.LivreBean;
import fr.biblio.configuration.Constantes;
import fr.biblio.proxies.LivreProxy;
import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Pret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private LivreProxy livreProxy;

    Logger log = LoggerFactory.getLogger(PretController.class);

    /**
     * Affiche la liste des prêts.
     */
    @GetMapping(value = "/prets")
    public List<Pret> listeDesPrets() {
        return pretRepository.findAll();
    }

    /**
     * Affiche un prêt de par son ID.
     */
    @GetMapping(value = "/prets/{id}")
    public Pret pret(@PathVariable("id") long id) {
        return pretRepository.findById(id).get();
    }

    /**
     * Affiche la liste des prêts d'un utilisateur.
     */
    @GetMapping(value = "/pretUtilisateur/{utilisateurId}")
    public List<Pret> pretUtilisateur(@PathVariable("utilisateurId") long utilisateurId) {

        List<Pret> prets = pretRepository.findByUtilisateurIdAndStatut(utilisateurId, Constantes.PRET);
        for (Pret pret : prets) {
            ExemplaireLivre exemplaireLivre = livreProxy.exemplaire(pret.getExemplaireId());
            LivreBean livre = livreProxy.afficherUnLivre(exemplaireLivre.getLivreId());
            Bibliotheque bibliotheque = livreProxy.bibliotheque(exemplaireLivre.getBibliothequeId());

            pret.setTitreLivre(livre.getTitre());
            pret.setNomBiblio(bibliotheque.getNom());
        }
        return prets;
    }

    /**
     * Affiche la liste des prêts dont la date du prêt est passé.
     */
    @GetMapping(value = "/dateRetourPassee")
    public List<Pret> dateRetourPassee() {
        Date date = new Date();
        List<Pret> prets = pretRepository.findPretByStatutAndDateRetourBefore(Constantes.PRET, date);
        return prets;
    }

    /**
     * Permet de modifier le statut et la date retour une fois le livre rendu.
     */
    @PostMapping(value = "/livreRendu/{pretId}")
    public Pret livreRendu(@PathVariable("pretId") long pretId) {

        Pret pret = pretRepository.findById(pretId).get();

        pret.setStatut(Constantes.RENDU);
        pret.setDateRetour(new Date());

        return pretRepository.save(pret);
    }

    /**
     * Permet de prolonger un prêt.
     */
    @PostMapping(value = "/prolongation/{pretId}")
    public Pret prolongation(@PathVariable("pretId") long pretId) {

        Pret pret = pretRepository.findById(pretId).get();

        try {
            GregorianCalendar date = new GregorianCalendar();

            date.setTime(pret.getDateRetour());
            date.add(GregorianCalendar.DAY_OF_YEAR, +28);

            pret.setDateRetour(date.getTime());
            pret.setProlongation(pret.getProlongation()+1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pretRepository.save(pret);
    }

    /**
     * Permet d'enregistrer un prêt.
     */
    @PostMapping(value = "/ajoutPret")
    public Pret ajoutPret(@RequestBody Pret pret) {
        return pretRepository.save(pret);
    }

}
