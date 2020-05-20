package fr.biblio.beans;

import lombok.Data;

@Data
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

}
