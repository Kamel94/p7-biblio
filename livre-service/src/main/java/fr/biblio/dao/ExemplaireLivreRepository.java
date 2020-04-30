package fr.biblio.dao;

import fr.biblio.entities.ExemplaireLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExemplaireLivreRepository extends JpaRepository<ExemplaireLivre, Long> {

    ExemplaireLivre findExemplaireLivreByLivreId(long id);

    List<ExemplaireLivre> findByLivreId(long id);

    ExemplaireLivre findExemplaireLivreByLivreIdAndBibliothequeId(long livreId, long bibliothequeId);
}
