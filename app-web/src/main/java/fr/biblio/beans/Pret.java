package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    private Long exemplaireId;

    private Long utilisateurId;

    public Pret(Long id, Date datePret, Date dateRetour, String statut, int prolongation, Long exemplaireId, Long utilisateurId) {
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
