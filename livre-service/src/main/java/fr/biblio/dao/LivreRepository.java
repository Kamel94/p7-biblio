package fr.biblio.dao;

import fr.biblio.entities.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LivreRepository extends JpaRepository<Livre, Long> {

    @Query("select l from Livre l where lower(l.titre) like lower(:titre) and lower(l.auteur) like lower(:auteur) and l.categorie like lower(:categorie)")
    List<Livre> recherche(@Param("titre") String titre, @Param("auteur") String auteur, @Param("categorie") String categorie);

}
