package nl.rabobank.powerofattorney.backingapi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateSerializer() {
        super();
        this.formatter = Constant.dateFormatter();
    }


    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(formatter.format(value));
    }
}
