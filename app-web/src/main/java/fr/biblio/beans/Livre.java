package fr.biblio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Livre {

    private Long id;

    private String nom;

    private String auteur;

    private Date publication;

}
