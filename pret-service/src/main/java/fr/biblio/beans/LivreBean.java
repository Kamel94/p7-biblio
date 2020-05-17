package fr.biblio.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.biblio.entities.Categorie;
import fr.biblio.entities.ExemplaireLivre;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LivreBean {

    private Long id;
    private String titre;
    private String auteur;
    private long categorieId;
    private String editeur;
    private Date edition;
    private String couverture;
    private String description;

    private Categorie categorie;
    private Set<ExemplaireLivre> exemplaireLivres;
}
