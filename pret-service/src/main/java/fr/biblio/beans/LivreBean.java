package fr.biblio.beans;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LivreBean {

    private Long id;

    private String nom;

    private String auteur;

    private Date publication;
}
