package nl.rabobank.powerofattorney.model.attorney;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PowerOfAttorneyReference {
    @NonNull AttorneyId id;
}

