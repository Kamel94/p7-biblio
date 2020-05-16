package fr.biblio.controller;

import fr.biblio.configuration.Constantes;
import fr.biblio.dto.PretDto;
import fr.biblio.mapper.PretMapper;
import fr.biblio.service.ComparePret;
import fr.biblio.service.ICompareDate;
import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PretController {

    @Autowired
    PretRepository pretRepository;

    @Qualifier("compareDate")
    @Autowired
    private ICompareDate icompareDate;

    @Autowired
    private PretMapper mapper;

    @Autowired
    private ComparePret comparePret;

    @RequestMapping(value = "/prets")
    public List<PretDto> listeDesPrets() {
        return mapper.listePret(pretRepository.findAll());
    }

    @GetMapping(value = "/prets/{id}")
    public Pret pret(@PathVariable("id") long id) {
        return pretRepository.findById(id).get();
    }

    @GetMapping(value = "/pretUtilisateur/{utilisateurId}/{statut}")
    public List<Pret> pretUtilisateur(@PathVariable("utilisateurId") long utilisateurId, @PathVariable("statut") String statut) {
        return pretRepository.findByUtilisateurIdAndStatut(utilisateurId, statut);
    }

    @GetMapping("/pretsFini")
    public List<Pret> afficheLaListeDesPretsFini() {

        List<Pret> pretStatut = pretRepository.findPretByStatut(Constantes.PRET);

        return pretStatut;
    }

    @GetMapping(value = "/datePassee")
    public List<Pret> datePassee() {
        Date date = new Date();
        List<Pret> prets = pretRepository.findByDateRetourBefore(date);
        return prets;
    }

    @GetMapping(value = "/retardRetour")
    public List<Pret> retardRetour() {
        List<Pret> retardRetour = pretRepository.findPretByStatut(Constantes.FIN);
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
