package nl.rabobank.powerofattorney.model.enums;

public enum Authorization {
    DEBIT_CARD("debit card"),
    VIEW("view"),
    PAYMENT("payment");

    private final String value;

    Authorization(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
