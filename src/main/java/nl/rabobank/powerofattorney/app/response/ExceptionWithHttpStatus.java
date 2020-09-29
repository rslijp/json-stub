package nl.rabobank.powerofattorney.app.response;

import org.springframework.http.HttpStatus;

public class ExceptionWithHttpStatus extends Exception {

    private HttpStatus status;

    public ExceptionWithHttpStatus(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus status() {
        return status;
    }
}
