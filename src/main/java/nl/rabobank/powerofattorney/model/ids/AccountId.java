package nl.rabobank.powerofattorney.model.ids;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Data
@JsonSerialize(using = AccountId.Serializer.class)
@JsonDeserialize(using = AccountId.Deserializer.class)
public class AccountId {
    @NonNull final String value;

    public static class Serializer extends JsonSerializer<AccountId> {
        @SneakyThrows
        @Override
        public void serialize(AccountId value, JsonGenerator gen, SerializerProvider serializers) {
            gen.writeString(value.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<AccountId> {
        @SneakyThrows
        @Override
        public AccountId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            var id = jsonParser.getValueAsString();
            return id != null ? new AccountId(id) : null;
        }
    }
}


