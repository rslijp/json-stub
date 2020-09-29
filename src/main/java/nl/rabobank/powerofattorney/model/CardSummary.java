package nl.rabobank.powerofattorney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSummary {
    @NonNull String id;
    @NonNull CardType type;
}