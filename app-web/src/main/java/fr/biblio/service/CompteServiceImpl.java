package fr.biblio.service;

import fr.biblio.beans.Utilisateur;
import fr.biblio.proxies.WebProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private WebProxy webProxy;

    private static final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {

        return webProxy.ajouterUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return webProxy.getUtilisateurWithEmail(email);
    }
}
