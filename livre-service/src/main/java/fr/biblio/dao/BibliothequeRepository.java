package fr.biblio.dao;

import fr.biblio.entities.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BibliothequeRepository extends JpaRepository<Bibliotheque, Long> {
}
