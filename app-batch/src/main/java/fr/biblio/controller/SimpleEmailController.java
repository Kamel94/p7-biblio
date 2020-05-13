package fr.biblio.controller;

import fr.biblio.job.ScheduledTask;
import fr.biblio.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;


@RestController
public class SimpleEmailController {

    @Autowired
    SimpleEmailService emailService;

    @Autowired
    private ScheduledTask task;

    private static final Logger log = LoggerFactory.getLogger(SimpleEmailController.class);

    @GetMapping(value = "/simple-email")
    public @ResponseBody ResponseEntity simpleEmail() {

        try {
            task.executeTask();
        } catch (MailException mailException) {
            log.error("Erreur lors de l'envoie du mail..{}", mailException.getStackTrace());
            return new ResponseEntity<>("Impossible d'envoyer l'email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

            return new ResponseEntity<>("Email envoyé !", HttpStatus.OK);
        }
}
