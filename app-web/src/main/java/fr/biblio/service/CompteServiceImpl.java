package fr.biblio.service;

import fr.biblio.beans.Utilisateur;
import fr.biblio.proxies.LivreProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private LivreProxy livreProxy;

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {

        String cryptPass = bCryptPasswordEncoder.encode(utilisateur.getPassword());

        utilisateur.setPassword(cryptPass);

        return livreProxy.ajouterUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return livreProxy.email(email);
    }
}
