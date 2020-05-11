package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExemplaireLivre {

    private Long id;
    private Long livreId;
    private Long bibliothequeId;
    private int numeroSerie;
    private int nombreExemplaire;
    private boolean disponibilite;

    private Livre livre;
    private Bibliotheque bibliotheque;

}
