package nl.rabobank.powerofattorney.model.attorneys;

import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;

public interface PowerOfAttorneyRepository {
    PowerOfAttorneyReference[] all();

    @NonNull PowerOfAttorney get(@NonNull AttorneyId id);
}
