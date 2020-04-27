package fr.biblio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivreIntrouvableException extends RuntimeException {
    public LivreIntrouvableException(String s) {
        super(s);
    }
}
