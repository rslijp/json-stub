package nl.rabobank.powerofattorney.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtil {

    @SneakyThrows
    public static <T> T asClass(Class<T> clazz, String json) {
        return new ObjectMapper().readValue(json, clazz);
    }

}
