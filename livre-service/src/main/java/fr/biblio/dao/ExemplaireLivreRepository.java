package fr.biblio.dao;

import fr.biblio.entities.ExemplaireLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExemplaireLivreRepository extends JpaRepository<ExemplaireLivre, Long> {

    ExemplaireLivre findExemplaireLivreByLivreId(long id);
}
