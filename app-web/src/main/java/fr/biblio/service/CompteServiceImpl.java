package fr.biblio.service;

import fr.biblio.beans.Utilisateur;
import fr.biblio.proxies.LivreProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private LivreProxy livreProxy;

    private static final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {

        return livreProxy.ajouterUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return livreProxy.getUtilisateurWithEmail(email);
    }
}
