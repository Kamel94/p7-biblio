package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name = "id_genre", referencedColumnName = "id", insertable= false, updatable= false)
    private Genre genre;
}
