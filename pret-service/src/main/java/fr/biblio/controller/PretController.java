package fr.biblio.controller;

import fr.biblio.configuration.Constantes;
import fr.biblio.service.ICompareDate;
import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PretController {

    @Autowired
    PretRepository pretRepository;

    @Qualifier("compareDate")
    @Autowired
    private ICompareDate icompareDate;

    @RequestMapping(value = "/prets")
    public List<Pret> listeDesPrets() {

        List<Pret> prets = pretRepository.findAll();

        return prets;
    }

    @GetMapping(value = "/prets/{id}")
    public Pret pret(@PathVariable("id") long id) {
        return pretRepository.findById(id).get();
    }

    @GetMapping(value = "/finPret")
    public List<Pret> finDuPret() {

        List<Pret> pretStatut = pretRepository.findPretByStatut("PRET");

        for (int i = 0; i < pretStatut.size(); i++) {

            String compareDate = icompareDate.compareDateWithToday(pretStatut.get(i).getDateRetourString());

            if (compareDate.equals(Constantes.APRES)) {
                pretStatut.get(i).setStatut("FIN");
            }
        }

        return pretRepository.saveAll(pretStatut);
    }

    @GetMapping(value = "/retardRetour")
    public List<Pret> retardRetour() {
        List<Pret> retardRetour = pretRepository.findPretByStatut("FIN");
        List<Pret> listeLivreEnRetard = new ArrayList<>();
        String compareDate = "";
        for (int i = 0; i < retardRetour.size(); i++) {
            compareDate = icompareDate.compareDateWithToday(retardRetour.get(i).getDateRetourString());
            if (compareDate.equals(Constantes.APRES)) {
                listeLivreEnRetard.add(retardRetour.get(i));
            }
        }
        return listeLivreEnRetard;
    }

    @PostMapping(value = "/ajoutPret")
    public Pret ajoutPret(@RequestBody Pret pret) {
        return pretRepository.save(pret);
    }

}
