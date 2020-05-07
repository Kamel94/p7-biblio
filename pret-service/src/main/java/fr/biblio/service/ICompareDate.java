package fr.biblio.service;

import org.springframework.stereotype.Service;

@Service
public interface ICompareDate {

    String todayDate();
    String compareDateWithToday(String pDate);

}
