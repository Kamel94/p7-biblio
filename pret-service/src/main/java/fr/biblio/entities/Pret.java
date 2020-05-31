package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private int prolongation;

    private Long exemplaireId;

    private Long utilisateurId;

    @Transient
    private String titreLivre;
    @Transient
    private String nomBiblio;
    @Transient
    private String dateRetourString;
    @Transient
    private int numeroSerieExemplaire;

}
