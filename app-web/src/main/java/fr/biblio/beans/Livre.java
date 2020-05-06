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

    private String description;

    private Set<ExemplaireLivre> exemplaireLivres;

}
