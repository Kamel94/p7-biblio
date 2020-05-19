package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livre {

    private Long id;
    private String titre;
    private String auteur;
    private long categorieId;
    private String editeur;
    private Date edition;
    private String editionString;
    private String couverture;
    private String description;
    private Categorie categorie;
    private Set<ExemplaireLivre> exemplaireLivres;

}
