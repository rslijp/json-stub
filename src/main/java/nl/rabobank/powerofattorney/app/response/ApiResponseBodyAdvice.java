package nl.rabobank.powerofattorney.app.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@RestControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ApiResponseBodyAdvice.class);

    @Override
    public boolean supports(final MethodParameter methodParameter, final Class aClass) {
        return !aClass.equals(ByteArrayHttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(final Object o, final MethodParameter methodParameter, final MediaType mediaType,
                                  final Class aClass, final ServerHttpRequest serverHttpRequest,
                                  final ServerHttpResponse serverHttpResponse) {
        if (o instanceof GateWayException) {
            serverHttpResponse.setStatusCode(HttpStatus.BAD_GATEWAY);
            return null;
        }
        if (o instanceof NotFoundException) {
            serverHttpResponse.setStatusCode(HttpStatus.NOT_FOUND);
            return null;
        }
        if (o instanceof ResponseWrapper) {
            return o;
        }
        return ResponseWrapper.success(o);
    }

    @ExceptionHandler(GateWayException.class)
    public Object handleBadGatewayException(GateWayException ex) {
        LOG.error("Encountered a bad gateway during API call. " + ex.getMessage());
        return ex;
    }

    @ExceptionHandler(NotFoundException.class)
    public Object handleOtherException(NotFoundException ex) {
        LOG.error("Requested entity in API call wasn't found. " + ex.getMessage());
        return ex;
    }

    @ExceptionHandler(Exception.class)
    public Object handleOtherException(Exception ex) {
        LOG.error("Received error on API call. " + ex.getMessage(), ex);
        return ResponseWrapper.error();
    }
}
