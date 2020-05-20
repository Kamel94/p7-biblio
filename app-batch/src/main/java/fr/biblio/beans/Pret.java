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
    private Long exemplaireId;
    private Long utilisateurId;

}
