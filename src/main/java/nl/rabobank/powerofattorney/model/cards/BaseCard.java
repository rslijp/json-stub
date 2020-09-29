package nl.rabobank.powerofattorney.model.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardStatus;
import nl.rabobank.powerofattorney.model.ids.CardId;


@NoArgsConstructor
@AllArgsConstructor
@Data
abstract class BaseCard {
    @NonNull CardId id;
    CardStatus status;
    int cardNumber;
    int sequenceNumber;
    @NonNull String cardHolder;

}
