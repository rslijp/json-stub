package nl.rabobank.powerofattorney.model.attorney;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.cards.CardSummary;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.Direction;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attorney {
    @NonNull AttorneyId id;
    @NonNull String grantor;
    @NonNull String grantee;
    @NonNull String account;
    @NonNull Direction direction;
    @NonNull Authorization[] authorizations = new Authorization[]{};
    @NonNull CardSummary[] cards = new CardSummary[]{};
}

