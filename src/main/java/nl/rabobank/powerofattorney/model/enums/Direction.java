package nl.rabobank.powerofattorney.model.enums;

public enum Direction {
    GIVEN("given"),
    RECEIVED("received");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
