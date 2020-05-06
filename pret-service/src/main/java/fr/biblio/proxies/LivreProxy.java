package fr.biblio.proxies;

import fr.biblio.beans.LivreBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
@RibbonClient(name = "livre-service")
public interface LivreProxy {

    @GetMapping(value = "/livre-service/livres")
    List<LivreBean> listeDesLivres();

    @GetMapping(value = "/livre-service/livres/{id}")
    LivreBean afficherUnLivre(@PathVariable(name = "id") Long id);

   /* @GetMapping(value = "/authentification-service/listeUtilisateurs")
    List<Utilisateur> listeUtilisateurs();

    @GetMapping(value = "/authentification-service/listeUtilisateurs/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id); */

}
