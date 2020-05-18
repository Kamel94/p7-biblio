package fr.biblio.job;

import fr.biblio.beans.*;
import fr.biblio.proxy.BatchProxy;
import fr.biblio.service.DateFormat;
import fr.biblio.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
@ConditionalOnExpression("'${scheduler.enabled}'=='true'")
public class ScheduledTask {

    private final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private BatchProxy batchProxy;

    @Autowired
    private DateFormat dateFormat;

    @Autowired
    private SimpleEmailService emailService;

    @Scheduled(cron = "0 * * ? * *", zone = "Europe/Paris")
    public void executeTask() {

        List<Pret> retourRetard = batchProxy.dateRetourPassee();

        try {
            for (Pret pret : retourRetard) {
                Utilisateur utilisateur = batchProxy.utilisateur(pret.getUtilisateurId());
                ExemplaireLivre exemplaireLivre = batchProxy.exemplaire(pret.getExemplaireId());
                Livre livre = batchProxy.afficherUnLivre(exemplaireLivre.getLivreId());
                Bibliotheque bibliotheque = batchProxy.bibliotheque(exemplaireLivre.getBibliothequeId());

                String date = dateFormat.dateRetour(pret.getId());
                String genre = "";
                String msgProlongement = "";
                String mail = utilisateur.getEmail();
                String destinataire = mail;
                String objet = "Rappel, la date du prêt est arrivée à échéance !";

                pret.setDateRetourString(date);

                if (utilisateur.getGenreId() == 1) {
                    genre = "Mr";
                } else {
                    genre = "Mme";
                }

                if (pret.getProlongation() == 0) {
                    msgProlongement = "\nJe vous rappelle que, si vous le voulez, vous pouvez prolonger votre prêt.";
                } else if (pret.getProlongation() == 1) {
                    msgProlongement = "\nJe vous informe que malheureusement vous ne pouvez plus prolonger ce prêt.";
                }

                String message = "Bonjour " + genre + " " + utilisateur.getNom() + "," +
                        "\n\nLa date de retour pour le livre " + "''" + livre.getTitre() + "''" +
                        " de " + livre.getAuteur() +
                        " était le " + pret.getDateRetourString() + "..." +
                        "\nMerci de rapporter le livre au plus tôt à la bibliothèque " +
                        bibliotheque.getNom() + "." +
                        "\n" + msgProlongement +
                        "\n\nService de la ville";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
