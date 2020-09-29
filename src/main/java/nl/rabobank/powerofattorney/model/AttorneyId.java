package nl.rabobank.powerofattorney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttorneyId {
    @NonNull String id;
}
