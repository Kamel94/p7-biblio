package fr.biblio.job;

import fr.biblio.beans.*;
import fr.biblio.proxy.BatchProxy;
import fr.biblio.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnExpression("'${scheduler.enabled}'=='true'")
public class ScheduledTask {

    private final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private BatchProxy batchProxy;

    @Autowired
    private SimpleEmailService emailService;

    @Scheduled(cron = "0 0 8 ? * * ", zone = "Europe/Paris")
    public void executeTask() {

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

                // envoie du mail
                log.info("****************************************************************************************");
                log.info("Rappel envoyé a: " + mail);
                log.info("****************************************************************************************");
                emailService.sendSimpleEmail(destinataire, objet, message);
            }
            if (retourRetard.isEmpty()) {
                log.info("****************************************************************************************");
                log.info("Il n'y a aucun email de rappel à envoyer.");
                log.info("****************************************************************************************");
            }
        } catch (MailException mailException) {
            log.error("Erreur lors de l'envoie du mail..{}", mailException.getStackTrace());
        }
    }

}
