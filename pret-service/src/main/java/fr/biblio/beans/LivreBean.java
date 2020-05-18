package fr.biblio.beans;

import lombok.*;

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

    private Set<ExemplaireLivre> exemplaireLivres;
}
