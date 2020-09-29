package nl.rabobank.powerofattorney.model.accounts;

import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.AccountId;

public interface AccountRepository {
    @NonNull Account get(@NonNull AccountId id);
}
