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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long exemplaireId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long utilisateurId;

    private ExemplaireLivre exemplaireLivre;

    private Utilisateur utilisateur;

}
