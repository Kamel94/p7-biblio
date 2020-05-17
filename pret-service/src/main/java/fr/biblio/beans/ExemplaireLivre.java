package fr.biblio.beans;

import fr.biblio.entities.Bibliotheque;
import fr.biblio.entities.Livre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExemplaireLivre {

    private Long id;

    private long livreId;

    private long bibliothequeId;

    private int numeroSerie;

    private boolean disponibilite;

    private int nombreExemplaire;

    private Livre livre;

    private Bibliotheque bibliotheque;

}
