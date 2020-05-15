package fr.biblio.service;

import fr.biblio.beans.Utilisateur;
import fr.biblio.proxies.LivreProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CompteService compteService;

    @Autowired
    private LivreProxy livreProxy;

    Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = compteService.findByEmail(email);
        if (utilisateur == null) throw new UsernameNotFoundException(email);

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        List<Utilisateur> utilisateurs = livreProxy.listeUtilisateurs();

        for (Utilisateur util : utilisateurs) {
            authorities.add(new SimpleGrantedAuthority(utilisateur.getStatut()));
        }

        log.info("email = " + utilisateur.getEmail() + "pass = " + utilisateur.getPassword()
                + "Statut = " + utilisateur.getStatut());

        return new User(utilisateur.getEmail(), utilisateur.getPassword(), authorities);
    }
}
