package nl.rabobank.powerofattorney.model.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.AccountId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountReference {
    @NonNull AccountId id;
}
