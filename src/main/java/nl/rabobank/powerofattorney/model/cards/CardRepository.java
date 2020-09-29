package nl.rabobank.powerofattorney.model.cards;

import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.CardId;

public interface CardRepository {
    DebitCard debitCard(CardId id);

    @NonNull CreditCard creditCard(CardId id);
}
