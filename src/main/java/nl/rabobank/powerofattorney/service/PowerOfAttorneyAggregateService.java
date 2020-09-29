package nl.rabobank.powerofattorney.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.model.accounts.AccountRepository;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.cards.CardRepository;
import nl.rabobank.powerofattorney.model.enums.CardType;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PowerOfAttorneyAggregateService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    public Optional<PowerOfAttorneyAggregate> aggregate(AttorneyId attorneyId) {
        var powerOfAttorney = powerOfAttorneyRepository.get(attorneyId);
        var account = accountRepository.get(powerOfAttorney.getAccount().asId());
        if (!account.isActive()) {
            return Optional.empty();
        }

        var debitCards = Arrays.stream(powerOfAttorney.getCards()).
                filter(card -> card.is(CardType.DEBIT_CARD)).
                map(card -> cardRepository.debitCard(card.getId())).
                collect(Collectors.toList());
        var creditCards = Arrays.stream(powerOfAttorney.getCards()).
                filter(card -> card.is(CardType.CREDIT_CARD)).
                map(card -> cardRepository.creditCard(card.getId())).
                collect(Collectors.toList());
        var result = PowerOfAttorneyAggregate.create(powerOfAttorney, account, debitCards, creditCards);
        return Optional.of(result);
    }

    public List<PowerOfAttorneyAggregate> search(@NonNull String user) {
        var attorneyId = Arrays.stream(powerOfAttorneyRepository.all()).
                map(poar -> powerOfAttorneyRepository.get(poar.getId())).
                filter(poa -> poa.getGrantee().equals(user)).
                map(PowerOfAttorney::getId).
                collect(Collectors.toList());
        return attorneyId.stream().
                map(this::aggregate).
                filter(Optional::isPresent).
                map(Optional::get).
                collect(Collectors.toList());

    }


}
