package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.biblio.beans.Bibliotheque;
import fr.biblio.beans.LivreBean;
import fr.biblio.beans.Utilisateur;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Pret implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datePret;

    private Date dateRetour;

    @Transient
    private String dateRetourString;

    private String statut;

    @Column(nullable = true)
    private int prolongation;

    private Long exemplaireId;

    private Long utilisateurId;

    @Transient
    private String titreLivre;

    @Transient
    private String nomBiblio;

}
