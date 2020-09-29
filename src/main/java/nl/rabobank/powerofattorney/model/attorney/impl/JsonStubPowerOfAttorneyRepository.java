package nl.rabobank.powerofattorney.model.attorney.impl;

import static nl.rabobank.powerofattorney.util.JsonUtil.asClass;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorneyReference;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JsonStubPowerOfAttorneyRepository implements PowerOfAttorneyRepository {

    private final ApiClient apiClient;

    @Override
    public PowerOfAttorneyReference[] all() {
        return asClass(PowerOfAttorneyReference[].class,
                apiClient.get("power-of-attorneys")
        );
    }

    @SneakyThrows
    public PowerOfAttorney get(@NonNull AttorneyId id) {
        return asClass(PowerOfAttorney.class,
                apiClient.get("power-of-attorneys", id.getValue())
        );
    }
}
