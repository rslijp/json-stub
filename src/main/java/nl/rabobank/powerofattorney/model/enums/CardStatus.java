package nl.rabobank.powerofattorney.model.enums;

public enum CardStatus {
    ACTIVE("active"),
    BLOCKED("blocked");

    private final String value;

    CardStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
