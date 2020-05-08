package fr.biblio.service;

import fr.biblio.configuration.StaticCompareDate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CompareDate implements ICompareDate {


    public String todayDate() {
        Calendar calendar = Calendar.getInstance();

        // Format
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDate.format(calendar.getTime());
    }

    public String compareDateWithToday(String pDate) {

        Date newDate = null;
        String compareDate = "";

        // Date actuelle
        Calendar todayDate = Calendar.getInstance();
        // Date pour la comparaison
        Calendar dateToCompare = Calendar.getInstance();

        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");

        try {
            newDate = dateFormated.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateToCompare.setTime(newDate);

       /* String todayDateString = dateFormated.format(todayDate.getTime());
        String dateToCompareString = dateFormated.format(dateToCompare.getTime());*/

        // Comparaison des dates
        if (todayDate.equals(dateToCompare)) {
            compareDate = StaticCompareDate.EGAL;
        } else if (todayDate.after(dateToCompare)) {
            compareDate = StaticCompareDate.APRES;
        } else if (todayDate.before(dateToCompare)) {
            compareDate = StaticCompareDate.AVANT;
        }

        return compareDate;
    }
}
