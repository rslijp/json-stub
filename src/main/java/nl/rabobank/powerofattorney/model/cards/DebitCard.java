package nl.rabobank.powerofattorney.model.cards;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardStatus;
import nl.rabobank.powerofattorney.model.ids.CardId;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class DebitCard extends BaseCard {
    @NonNull Limit atmLimit;
    @NonNull Limit posLimit;
    boolean contactless;

    public DebitCard(CardId id,
                     CardStatus status,
                     int cardNumber,
                     int sequenceNumber,
                     String cardHolder,
                     Limit atmLimit,
                     Limit posLimit,
                     boolean contactless) {
        super(id, status, cardNumber, sequenceNumber, cardHolder);
        this.atmLimit = atmLimit;
        this.posLimit = posLimit;
        this.contactless = contactless;
    }
}
