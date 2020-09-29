package nl.rabobank.powerofattorney.backingapi;

import java.io.IOException;

import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.app.response.GateWayException;
import nl.rabobank.powerofattorney.app.response.NotFoundException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(ApiClient.class);

    private final OkHttpClient httpClient = new OkHttpClient();
    private final String basePath = "http://localhost:8080";

    public String get(String path) {
        return call(String.format("%s/%s", basePath, path));
    }

    public String get(String path, String id) {
        return call(String.format("%s/%s/%s", basePath, path, id));
    }

    @SneakyThrows
    private String call(String fullPath) {
        var request = new Request.Builder()
                .url(fullPath)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                LOG.error(String.format("Sending call to stub api failed %s", response));
                throw new GateWayException("Sending call to stub api failed");
            }
            var body = response.body();
            if (body == null) {
                throw new NotFoundException("No such attorney");
            }
            return body.string();
        } catch (IOException e) {
            LOG.error("I/O Error during stub api call", e);
            throw new GateWayException("I/O Error during stub api call");
        }
    }

}
