package nl.rabobank.powerofattorney.model.enums;

public enum CardType {
    DEBIT_CARD("debit card"),
    CREDIT_CARD("credit card");

    private final String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
