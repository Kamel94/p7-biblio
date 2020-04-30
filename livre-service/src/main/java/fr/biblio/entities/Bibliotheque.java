package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Bibliotheque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;

    @OneToMany(mappedBy = "bibliotheque")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<ExemplaireLivre> exemplaireLivres;

}
