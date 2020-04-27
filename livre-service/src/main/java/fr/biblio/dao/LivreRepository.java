package fr.biblio.dao;

import fr.biblio.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LivreRepository extends JpaRepository<Livre, Long> {
}
