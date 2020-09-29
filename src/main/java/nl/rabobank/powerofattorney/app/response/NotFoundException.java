package nl.rabobank.powerofattorney.app.response;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ExceptionWithHttpStatus {

    public NotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
