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

    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;

    private boolean disponible;
    private String etat;

    @Column(nullable = true)
    private int nombreExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_bibliotheque")
    private Bibliotheque bibliotheque;

}
