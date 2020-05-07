package fr.biblio.controller;

import fr.biblio.beans.*;
import fr.biblio.proxy.BatchProxy;
import fr.biblio.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimpleEmailController {

    @Autowired
    SimpleEmailService emailService;

    @Autowired
    private BatchProxy batchProxy;

    private static final Logger log = LoggerFactory.getLogger(SimpleEmailController.class);

    @GetMapping(value = "/simple-email/{email}")
    public @ResponseBody ResponseEntity simpleEmail(@PathVariable("email") String email) {

        List<Pret> retourRetard = batchProxy.retardRetour();

            try {
                for (int i = 0; i < retourRetard.size(); i++) {
                    Utilisateur utilisateur = batchProxy.utilisateur(retourRetard.get(i).getUtilisateurId());
                    ExemplaireLivre exemplaireLivre = batchProxy.exemplaire(retourRetard.get(i).getExemplaireId());
                    Livre livre = batchProxy.afficherUnLivre(exemplaireLivre.getLivreId());
                    Bibliotheque bibliotheque = batchProxy.bibliotheque(exemplaireLivre.getBibliothequeId());

                    String mail = utilisateur.getEmail();
                    String destinataire = mail;
                    String objet = "Rappel, date de fin de réservation dépassé !";
                    String message = "Bonjour " + utilisateur.getNom() + "," +
                            "\nLa date de retour maximale pour le livre: " + livre.getTitre() +
                            " de " + livre.getAuteur() +
                            " était le: " + retourRetard.get(i).getDateRetourString() + " !" +
                            "\nMerci de ramener le livre au plus tôt dans la bibliothèque: " +
                            bibliotheque.getNom() + " !";

                    //send mail
                    log.info("****************************************************************************************");
                    log.info("Rappel envoye a: " + mail);
                    log.info("****************************************************************************************");
                    emailService.sendSimpleEmail(destinataire, objet, message);
                }
                if (retourRetard.isEmpty()) {
                    log.info("****************************************************************************************");
                    log.info("Il n'y a aucun email de rappel a envoyer.");
                    log.info("****************************************************************************************");
                }
            } catch (MailException mailException) {
                log.error("Erreur lors de l'envoie du mail..{}", mailException.getStackTrace());
                return new ResponseEntity<>("Impossible d'envoyer l'email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("Email envoyé !", HttpStatus.OK);
        }
}
