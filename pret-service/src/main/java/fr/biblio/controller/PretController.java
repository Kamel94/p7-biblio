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
    public Pret getPret(@PathVariable("id") long id) {
        return pretRepository.findById(id).get();
    }

    /**
     * Affiche la liste des prêts d'un utilisateur.
     */
    @GetMapping(value = "/pretUtilisateur/{utilisateurId}")
    public List<Pret> getPretsWithUtilisateurId(@PathVariable("utilisateurId") long utilisateurId) {

        List<Pret> prets = pretRepository.findByUtilisateurIdAndStatut(utilisateurId, Constantes.PRET);
        for (Pret pret : prets) {
            ExemplaireLivre exemplaireLivre = livreProxy.getExemplaire(pret.getExemplaireId());
            LivreBean livre = livreProxy.getLivre(exemplaireLivre.getLivreId());
            Bibliotheque bibliotheque = livreProxy.getBibliotheque(exemplaireLivre.getBibliothequeId());

            pret.setTitreLivre(livre.getTitre());
            pret.setNomBiblio(bibliotheque.getNom());
        }
        return prets;
    }

    /**
     * Affiche la liste des prêts dont la date du prêt est passé.
     */
    @GetMapping(value = "/dateRetourPassee")
    public List<Pret> getPretsFinished() {
        Date date = new Date();
        List<Pret> prets = pretRepository.findPretByStatutAndDateRetourBefore(Constantes.PRET, date);
        return prets;
    }

    /**
     * Permet de modifier le statut et la date retour une fois le livre rendu.
     */
    @PostMapping(value = "/livreRendu/{pretId}")
    public Pret rendreLivre(@PathVariable("pretId") long pretId) {

        Pret pret = pretRepository.findById(pretId).get();

        ExemplaireLivre exemplaireLivre = livreProxy.getExemplaire(pret.getExemplaireId());

        exemplaireLivre.setNombreExemplaire(exemplaireLivre.getNombreExemplaire() + 1);

        if (exemplaireLivre.getNombreExemplaire() > 0) {
            exemplaireLivre.setDisponibilite(true);
        }

        livreProxy.updateExemplaire(exemplaireLivre);

        pret.setStatut(Constantes.RENDU);
        pret.setDateRetour(new Date());

        return pretRepository.save(pret);
    }

    /**
     * Permet de prolonger un prêt.
     */
    @PostMapping(value = "/prolongation/{pretId}")
    public Pret prolongerPret(@PathVariable("pretId") long pretId) {

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
    @PostMapping(value = "/ajoutPret/{utilisateurId}/{exemplaireId}")
    public Pret addPret(@PathVariable("utilisateurId") long utilisateurId,
                          @PathVariable("exemplaireId") long exemplaireId) {

        Pret pret = new Pret();

        ExemplaireLivre exemplaireLivre = livreProxy.getExemplaire(exemplaireId);

        exemplaireLivre.setNombreExemplaire(exemplaireLivre.getNombreExemplaire() - 1);

        if (exemplaireLivre.getNombreExemplaire() == 0) {
            exemplaireLivre.setDisponibilite(false);
        }

        livreProxy.updateExemplaire(exemplaireLivre);

        try {
            GregorianCalendar date = new GregorianCalendar();

            pret.setDatePret(new Date());

            date.setTime(pret.getDatePret());
            date.add(GregorianCalendar.DAY_OF_YEAR, +28);

            pret.setUtilisateurId(utilisateurId);
            pret.setDateRetour(date.getTime());
            pret.setProlongation(0);
            pret.setExemplaireId(exemplaireLivre.getId());
            pret.setStatut("PRET");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pretRepository.save(pret);
    }

}
