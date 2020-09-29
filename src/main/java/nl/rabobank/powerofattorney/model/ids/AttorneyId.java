package nl.rabobank.powerofattorney.model.ids;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonSerialize(using = AttorneyId.Serializer.class)
@JsonDeserialize(using = AttorneyId.Deserializer.class)
public class AttorneyId {
    @NonNull String value;

    public static class Serializer extends JsonSerializer<AttorneyId> {
        @SneakyThrows
        @Override
        public void serialize(AttorneyId value, JsonGenerator gen, SerializerProvider serializers) {
            gen.writeString(value.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<AttorneyId> {
        @SneakyThrows
        @Override
        public AttorneyId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            var id = jsonParser.getValueAsString();
            return id != null ? new AttorneyId(id) : null;
        }
    }
}


