package nl.rabobank.powerofattorney.model.attorneys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.Direction;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PowerOfAttorney {
    @NonNull AttorneyId id;
    @NonNull String grantor;
    @NonNull String grantee;
    @NonNull BankAccountNumber account;
    @NonNull Direction direction;
    @NonNull Authorization[] authorizations = new Authorization[]{};
    @NonNull CardReference[] cards = new CardReference[]{};
}

