package nl.rabobank.powerofattorney.model.attorney;

import lombok.NonNull;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;

public interface PowerOfAttorneyRepository {
    PowerOfAttorneyReference[] all();
    PowerOfAttorney get(@NonNull AttorneyId id);
}
