package nl.rabobank.powerofattorney.model.ids;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonSerialize(using = CardId.Serializer.class)
@JsonDeserialize(using = CardId.Deserializer.class)
public class CardId {
    @NonNull String value;

    public static class Serializer extends JsonSerializer<CardId> {
        @SneakyThrows
        @Override
        public void serialize(CardId value, JsonGenerator gen, SerializerProvider serializers) {
            gen.writeString(value.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<CardId> {
        @SneakyThrows
        @Override
        public CardId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            var id = jsonParser.getValueAsString();
            return id != null ? new CardId(id) : null;
        }
    }
}
