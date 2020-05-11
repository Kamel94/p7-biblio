package fr.biblio.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Livre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;

    @Column(name = "id_categorie")
    private long categorieId;

    private String editeur;
    private Date edition;
    private String couverture;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_categorie", referencedColumnName = "id", insertable= false, updatable= false)
    private Categorie categorie;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "livre")
    private Set<ExemplaireLivre> exemplaireLivres;

}
