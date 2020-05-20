package fr.biblio.dao;

import fr.biblio.entities.ExemplaireLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExemplaireLivreRepository extends JpaRepository<ExemplaireLivre, Long> {

    List<ExemplaireLivre> findByLivreId(long id);

    ExemplaireLivre findExemplaireLivreByLivreIdAndBibliothequeId(long livreId, long bibliothequeId);
}
