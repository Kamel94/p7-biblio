package fr.biblio.proxies;

import fr.biblio.beans.LivreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "livre-service", url = "localhost:8081")
public interface LivreProxy {

    @GetMapping(value = "/livres")
    List<LivreBean> listeDesLivres();

    @GetMapping(value = "/livres/{id}")
    LivreBean afficherUnLivre(@PathVariable(name = "id") Long id);

}
