package fr.biblio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class PretDto {

    private Long id;

    private Date datePret;

    private Timestamp dateRetour;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String dateRetourString;

    private String statut;

    private int prolongation;

    private Long exemplaireId;

    private Long utilisateurId;

}
