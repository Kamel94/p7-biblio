package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.biblio.beans.Bibliotheque;
import fr.biblio.beans.LivreBean;
import fr.biblio.beans.Utilisateur;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Pret implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datePret;

    private Date dateRetour;

    private String statut;

    @Column(nullable = true)
    private boolean prolongation;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long exemplaireId;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long utilisateurId;

   /* @Transient
    private LivreBean livreBean;

    @Transient
    private Utilisateur utilisateur;

    @Transient
    private Bibliotheque bibliotheque;*/

}
