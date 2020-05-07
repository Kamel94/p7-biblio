package fr.biblio.service;

import fr.biblio.service.ICompareDate;
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

        //template
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");

        return simpleDate.format(calendar.getTime());
    }

    public String compareDateWithToday(String pDate) {
        Date newDate = null;
        String compareDate = "";

        Calendar todayDate = Calendar.getInstance(); // 1st calendar is current date/time
        Calendar dateToCompare = Calendar.getInstance(); // 2eme calendar is date to compare

        //template
        DateFormat dateFormated = new SimpleDateFormat("dd/MM/yyyy");

        // convert input on Date
        try {
            newDate = dateFormated.parse(pDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //set 2em calendar
        dateToCompare.setTime(newDate);

        //for display(check)
        String todayDateString = dateFormated.format(todayDate.getTime());
        String dateToCompareString = dateFormated.format(dateToCompare.getTime());

        //Compare dates
        if (todayDate.equals(dateToCompare)) {
            compareDate = "Aujourd'hui";
        } else if (todayDate.after(dateToCompare)) {
            compareDate = "Après";
        } else if (todayDate.before(dateToCompare)) {
            compareDate = "Avant";
        }

        return compareDate;

    }

}
