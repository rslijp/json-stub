package nl.rabobank.powerofattorney.model.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.rabobank.powerofattorney.model.enums.CardStatus;
import nl.rabobank.powerofattorney.model.ids.CardId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCard extends BaseCard {

    int monthlyLimit;

    public CreditCard(CardId id,
                      CardStatus status,
                      int cardNumber,
                      int sequenceNumber,
                      String cardHolder,
                      int monthlyLimit) {
        super(id, status, cardNumber, sequenceNumber, cardHolder);
        this.monthlyLimit = monthlyLimit;
    }
}
