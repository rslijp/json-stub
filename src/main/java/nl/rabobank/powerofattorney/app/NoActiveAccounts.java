package nl.rabobank.powerofattorney.app;

import nl.rabobank.powerofattorney.app.response.ExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class NoActiveAccounts extends ExceptionWithHttpStatus {

    public NoActiveAccounts(String id) {
        super("No active bank accounts found for " + id, HttpStatus.PRECONDITION_FAILED);
    }
}
