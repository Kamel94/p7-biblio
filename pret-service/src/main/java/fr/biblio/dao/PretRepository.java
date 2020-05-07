package fr.biblio.dao;

import fr.biblio.entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PretRepository extends JpaRepository<Pret, Long> {

    Pret findByStatut(String statut);

    List<Pret> findPretByStatut(String statut);

}
