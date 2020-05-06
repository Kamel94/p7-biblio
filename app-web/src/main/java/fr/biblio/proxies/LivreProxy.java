package fr.biblio.proxies;

import fr.biblio.beans.Bibliotheque;
import fr.biblio.beans.ExemplaireLivre;
import fr.biblio.beans.Livre;
import fr.biblio.beans.Pret;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.List;

@FeignClient(name = "zuul-server", url = "localhost:8888")
public interface LivreProxy {

    @GetMapping(value = "/livre-service/livres")
    List<Livre> listeDesLivres();

    @GetMapping(value = "/livre-service/livres/{id}")
    Livre afficherUnLivre(@PathVariable(name = "id") long id);

    @GetMapping(value = "/livre-service/exemplaireLivres/{livreId}")
    List<ExemplaireLivre> exemplaireParLivre(@PathVariable("livreId") long id);

    @GetMapping(value = "/livre-service/listeBibliotheques")
    List<Bibliotheque> listeDesBibliotheques();

    @GetMapping(value = "/livre-service/listeBibliotheques/{id}")
    Bibliotheque bibliotheque(@PathVariable("id") long id);

    @PostMapping(value = "/pret-service/ajoutPret/{id}")
    Pret ajoutPret(Pret pret);

}
