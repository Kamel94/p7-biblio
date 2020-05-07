package fr.biblio.controller;

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

    @GetMapping(value = "/prets/{statut}")
    public Pret statut(@PathVariable("statut") String statut) {
        return pretRepository.findByStatut(statut);
    }

    @GetMapping(value = "/retardRetour")
    public List<Pret> retardRetour() {
        List<Pret> retardRetour = pretRepository.findPretByStatut("Fin");
        List<Pret> listeLivreEnRetard = new ArrayList<>();
        String compareDate = "";
        for (int i = 0; i < retardRetour.size(); i++) {
            compareDate = icompareDate.compareDateWithToday(retardRetour.get(i).getDateRetourString());
            if (compareDate.equals("Après")) {
                listeLivreEnRetard.add(retardRetour.get(i));
            }
        }
        return listeLivreEnRetard;
    }

    @PostMapping(value = "/ajoutPret")
    public Pret ajoutPret(@RequestBody Pret pret) {
        return pretRepository.save(pret);
    }

   /* @RequestMapping("/")
    public String accueil(Model model) {

        List<LivreBean> livres =  livreProxy.listeDesLivres();

        model.addAttribute("livres", livres);

        return "test";
    }

    @PostMapping("/ajout")
    public String ajout(Model model) {

        List<LivreBean> livreBeans = livreProxy.listeDesLivres();

        livreBeans.forEach(l -> {
            pretRepository.save(new Pret(null, new Date(), new Date(), null, false, l.getId(), null, null, null, null, null));
        });

        return "ajout";
    }*/

}
