package nl.rabobank.powerofattorney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitCard {
    @NonNull String id;
    CardStatus status;
    int cardNumber;
    int sequenceNumber;
    @NonNull String cardHolder;
    @NonNull Limit atmLimit;
    @NonNull Limit posLimit;
    boolean contactless;
}
