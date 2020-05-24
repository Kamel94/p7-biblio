package fr.biblio.service;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FormaterDate {

    public String dateRetour(Date dateRetour) {

            String formaterDate = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formaterDate);

            String date = simpleDateFormat.format(dateRetour);

            return date;
    }

}
