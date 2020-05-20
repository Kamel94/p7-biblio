package fr.biblio.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(name = "id_genre")
    private long genreId;

    @Column(unique = true)
    private String email;
    private String password;
    private String telephone;
    private String adresse;
    private String statut;
    private boolean actif;

    @Column(name = "utilisateur_createur")
    private Integer utilisateurCreateur;

    @Column(name = "utilisateur_modif")
    private Integer utilisateurModif;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @ManyToOne
    @JoinColumn(name = "id_genre", referencedColumnName = "id", insertable= false, updatable= false)
    private Genre genre;
}
