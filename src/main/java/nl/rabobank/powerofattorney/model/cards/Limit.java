package nl.rabobank.powerofattorney.model.cards;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.rabobank.powerofattorney.model.enums.PeriodUnit;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Limit {
    int limit;
    PeriodUnit periodUnit;
}
