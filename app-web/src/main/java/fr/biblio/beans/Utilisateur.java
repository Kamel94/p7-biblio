package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur {

    private Long id;
    private String nom;
    private String prenom;
    private long genreId;
    private String email;
    private String password;
    private String telephone;
    private String adresse;
    private String statut;
    private boolean actif;
    private Integer utilisateurCreateur;
    private Integer utilisateurModif;
    private Timestamp dateCreation;
    private Timestamp dateModif;

}
