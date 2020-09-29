package nl.rabobank.powerofattorney.model.attorney;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.rabobank.powerofattorney.model.enums.CardType;
import nl.rabobank.powerofattorney.model.ids.CardId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardReference {
    @NonNull CardId id;
    @NonNull CardType type;
}
