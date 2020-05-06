package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bibliotheque {

    private Long id;
    private String nom;
    private String adresse;

    private Set<ExemplaireLivre> exemplaireLivres;

}
