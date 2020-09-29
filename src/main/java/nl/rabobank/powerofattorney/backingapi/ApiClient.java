package nl.rabobank.powerofattorney.backingapi;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.app.response.GateWayException;
import nl.rabobank.powerofattorney.app.response.NotFoundException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApiClient {
    private static final Logger LOG = LoggerFactory.getLogger(ApiClient.class);

    private final OkHttpClient httpClient = new OkHttpClient();
    private final String basePath;

    public ApiClient() {
        this("http://localhost:8080");
    }

    public String get(String entity) {
        return call(String.format("%s/%s", basePath, entity), entity);
    }

    public String get(String entity, String id) {
        return call(String.format("%s/%s/%s", basePath, entity, id), entity);
    }

    @SneakyThrows
    private String call(String fullPath, String entity) {
        var request = new Request.Builder()
                .url(fullPath)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                LOG.error(String.format("Sending call to stub api failed %s", response));
                if (response.code() == 404) {
                    throw new NotFoundException(String.format("Not found in %s", entity));
                }
                throw new GateWayException("Sending call to stub api failed");
            }
            var body = response.body();
            if (body == null) {
                return null;
            }
            return body.string();
        } catch (IOException e) {
            LOG.error("I/O Error during stub api call", e);
            throw new GateWayException("I/O Error during stub api call");
        }
    }

}
