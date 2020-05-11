package fr.biblio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ExemplaireLivre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_livre")
    private long livreId;

    @Column(name = "id_bibliotheque")
    private long bibliothequeId;

    private int numeroSerie;

    private boolean disponibilite;

    private int nombreExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_livre", referencedColumnName = "id", insertable= false, updatable= false)
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "id_bibliotheque", referencedColumnName = "id", insertable= false, updatable= false)
    private Bibliotheque bibliotheque;

}
