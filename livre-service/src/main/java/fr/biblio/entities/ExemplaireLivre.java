package fr.biblio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name = "id_livre", referencedColumnName = "id", insertable= false, updatable= false)
    private Livre livre;

    @Column(nullable = true)
    private int nombreExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_bibliotheque", referencedColumnName = "id", insertable= false, updatable= false)
    private Bibliotheque bibliotheque;

}
