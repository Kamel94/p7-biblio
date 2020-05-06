package fr.biblio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleEmail(String destinataire, String objet, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(destinataire);
        simpleMailMessage.setSubject(objet);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);

    }

}
