package nl.rabobank.powerofattorney.service;

import java.util.List;

import lombok.NonNull;
import lombok.Value;
import nl.rabobank.powerofattorney.model.accounts.Account;
import nl.rabobank.powerofattorney.model.attorneys.BankAccountNumber;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.cards.CreditCard;
import nl.rabobank.powerofattorney.model.cards.DebitCard;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.Direction;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;


@Value
public class PowerOfAttorneyAggregate {
    @NonNull
    final AttorneyId id;
    @NonNull
    final String grantor;
    @NonNull
    final String grantee;
    @NonNull
    final BankAccountNumber accountNumber;
    @NonNull
    final Account account;
    @NonNull
    final Direction direction;
    @NonNull
    final List<Authorization> authorizations;
    @NonNull
    final List<DebitCard> debitCards;
    @NonNull
    final List<CreditCard> creditCard;


    public static PowerOfAttorneyAggregate create(
            PowerOfAttorney powerOfAttorney,
            Account account,
            List<DebitCard> debitCards,
            List<CreditCard> creditCards) {
        return new PowerOfAttorneyAggregate(
                powerOfAttorney.getId(),
                powerOfAttorney.getGrantor(),
                powerOfAttorney.getGrantee(),
                powerOfAttorney.getAccount(),
                account,
                powerOfAttorney.getDirection(),
                List.of(powerOfAttorney.getAuthorizations()),
                debitCards,
                creditCards
        );
    }
}
