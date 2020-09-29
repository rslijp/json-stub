package nl.rabobank.powerofattorney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.Direction;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attorney {
    @NonNull String id;
    @NonNull String grantor;
    @NonNull String grantee;
    @NonNull String account;
    @NonNull Direction direction;
    @NonNull Authorization[] authorizations = new Authorization[]{};
    @NonNull CardSummary[] cards = new CardSummary[]{};
}

