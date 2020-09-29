package nl.rabobank.powerofattorney.model.accounts;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.backingapi.LocalDateDeserializer;
import nl.rabobank.powerofattorney.backingapi.LocalDateSerializer;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @NonNull String owner;

    int balance;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NonNull LocalDate created;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate ended;

    public boolean isActive() {
        var now = LocalDate.now();
        if (now.isBefore(created)) {
            return false;
        }
        if (ended == null) {
            return true;
        }
        return now.isBefore(ended);
    }


}
