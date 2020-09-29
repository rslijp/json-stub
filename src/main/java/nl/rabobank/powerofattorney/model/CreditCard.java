package nl.rabobank.powerofattorney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCard {
    @NonNull String id;
    CardStatus status;
    int cardNumber;
    int sequenceNumber;
    @NonNull String cardHolder;
    int monthlyLimit;
}
