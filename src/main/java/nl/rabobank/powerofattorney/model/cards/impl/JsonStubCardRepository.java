package nl.rabobank.powerofattorney.model.cards.impl;

import static nl.rabobank.powerofattorney.util.JsonUtil.asClass;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.cards.CardRepository;
import nl.rabobank.powerofattorney.model.cards.CreditCard;
import nl.rabobank.powerofattorney.model.cards.DebitCard;
import nl.rabobank.powerofattorney.model.ids.CardId;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonStubCardRepository implements CardRepository {
    private final ApiClient apiClient;

    @Override
    @SneakyThrows
    public DebitCard debitCard(CardId id) {
        return asClass(DebitCard.class,
                apiClient.get("debit-cards", id.getValue())
        );
    }

    @Override
    @SneakyThrows
    public CreditCard creditCard(CardId id) {
        return asClass(CreditCard.class,
                apiClient.get("credit-cards", id.getValue())
        );
    }
}
