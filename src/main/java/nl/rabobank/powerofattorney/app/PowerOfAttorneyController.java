package nl.rabobank.powerofattorney.app;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import nl.rabobank.powerofattorney.service.PowerOfAttorneyAggregate;
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
    @GetMapping(path = "/by-id/{id}")
    public @ResponseBody
    PowerOfAttorneyAggregate byId(@PathVariable String id) {
        var result = powerOfAttorneyAggregateService.aggregate(new AttorneyId(id));
        if (result.isEmpty()) {
            throw new NoActiveAccounts(id);
        }
        return result.get();
    }

    @SneakyThrows
    @GetMapping(path = "/by-user/{user}")
    public @ResponseBody
    List<PowerOfAttorneyAggregate> byName(@PathVariable String user) {
        return powerOfAttorneyAggregateService.search(user);
    }
}
