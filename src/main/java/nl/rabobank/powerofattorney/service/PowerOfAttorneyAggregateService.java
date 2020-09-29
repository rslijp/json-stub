package nl.rabobank.powerofattorney.service;

import lombok.RequiredArgsConstructor;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PowerOfAttorneyAggregateService {
    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    public PowerOfAttorney aggregate(AttorneyId attorneyId) {
        return powerOfAttorneyRepository.get(attorneyId);
    }
}
