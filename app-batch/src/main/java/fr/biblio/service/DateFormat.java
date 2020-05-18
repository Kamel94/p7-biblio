package fr.biblio.service;

import fr.biblio.beans.Pret;
import fr.biblio.proxy.BatchProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class DateFormat {

    @Autowired
    private BatchProxy batchProxy;

    public String dateRetour(long id) {

            String formatDate = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

            Pret pret = batchProxy.pret(id);

            String date = simpleDateFormat.format(pret.getDateRetour());

            return date;
    }

}
