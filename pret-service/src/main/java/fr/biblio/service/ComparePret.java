package fr.biblio.service;

import fr.biblio.configuration.Constantes;
import fr.biblio.dao.PretRepository;
import fr.biblio.entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComparePret {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ICompareDate iCompareDate;

    public String comparePret() {

        List<Pret> pretStatut = pretRepository.findPretByStatut(Constantes.PRET);

        String compareDate = "";

        for (int i = 0; i < pretStatut.size(); i++) {

            compareDate = iCompareDate.compareDateWithToday(pretStatut.get(i).getDateRetourString());
        }
        return compareDate;
    }

}
