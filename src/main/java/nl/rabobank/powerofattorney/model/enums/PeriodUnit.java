package nl.rabobank.powerofattorney.model.enums;

public enum PeriodUnit {
    PER_DAY("per day"),
    PER_WEEK("per week"),
    PER_MONTH("per month");

    private final String value;

    PeriodUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}