package fr.biblio.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pret {

    private Long id;

    private Date datePret;

    private Date dateRetour;

    private String dateRetourString;

    private String statut;

    private int prolongation;

    private String utilisateurNom;

    private String utilisateurPrenom;

    private long genreId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long exemplaireId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long utilisateurId;

    public Pret(Long id, Timestamp datePret, Date dateRetour, String statut, int prolongation, Long exemplaireId, Long utilisateurId) {
        this.id = id;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
        this.statut = statut;
        this.prolongation = prolongation;
        this.exemplaireId = exemplaireId;
        this.utilisateurId = utilisateurId;
    }

    private ExemplaireLivre exemplaireLivre;
    private Utilisateur utilisateur;

    private String titreLivre;

    private String nomBiblio;

}
