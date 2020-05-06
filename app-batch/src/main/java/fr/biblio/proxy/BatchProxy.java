package fr.biblio.proxy;

import fr.biblio.beans.Utilisateur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface BatchProxy {

    @GetMapping(value = "/authentification-service/listeUtilisateurs/{id}")
    Utilisateur utilisateur(@PathVariable("id") long id);

}
