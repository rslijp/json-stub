package nl.rabobank.powerofattorney.model.attorneys;

import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import nl.rabobank.powerofattorney.model.ids.AccountId;

@Data
@JsonSerialize(using = BankAccountNumber.Serializer.class)
@JsonDeserialize(using = BankAccountNumber.Deserializer.class)
public class BankAccountNumber {
    final @NonNull String countyCode;
    final @NonNull String checkSum;
    final @NonNull String bank;
    final @NonNull String accountNumber;

    public BankAccountNumber(@NonNull String iban) {
        var matcher = Pattern.compile("^([a-zA-Z]{2})([0-9]{2})([a-zA-Z0-9]{4})([0-9]{9})$");
        var match = matcher.matcher(iban);
        if (!match.matches()) {
            throw new IllegalArgumentException("This is not an iban " + iban);
        }
        countyCode = match.group(1);
        checkSum = match.group(2);
        bank = match.group(3);
        accountNumber = match.group(4);
    }

    public String getValue() {
        return String.format("%s%s%s%s", countyCode, checkSum, bank, accountNumber);
    }

    public AccountId asId() {
        return new AccountId(accountNumber);
    }

    public static class Serializer extends JsonSerializer<BankAccountNumber> {
        @SneakyThrows
        @Override
        public void serialize(BankAccountNumber value, JsonGenerator gen, SerializerProvider serializers) {
            gen.writeString(value.getValue());
        }
    }

    public static class Deserializer extends JsonDeserializer<BankAccountNumber> {
        @SneakyThrows
        @Override
        public BankAccountNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            var accountNumber = jsonParser.getValueAsString();
            return accountNumber != null ? new BankAccountNumber(accountNumber) : null;
        }
    }
}


