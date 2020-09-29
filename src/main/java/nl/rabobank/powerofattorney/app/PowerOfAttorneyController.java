package nl.rabobank.powerofattorney.app;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.model.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import nl.rabobank.powerofattorney.service.PowerOfAttorneyAggregateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PowerOfAttorneyController {

    private final PowerOfAttorneyAggregateService powerOfAttorneyAggregateService;

    @SneakyThrows
    @GetMapping(path = "/{id}")
    public @ResponseBody
    PowerOfAttorney aggregate(@PathVariable String id) {
       return powerOfAttorneyAggregateService.aggregate(new AttorneyId(id));
    }

}
