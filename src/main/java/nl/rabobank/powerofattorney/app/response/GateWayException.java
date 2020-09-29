package nl.rabobank.powerofattorney.app.response;

import org.springframework.http.HttpStatus;

public class GateWayException extends ExceptionWithHttpStatus {

    public GateWayException(String msg) {
        super(msg, HttpStatus.BAD_GATEWAY);
    }
}
