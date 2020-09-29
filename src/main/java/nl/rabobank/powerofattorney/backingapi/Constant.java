package nl.rabobank.powerofattorney.backingapi;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Constant {
    static String DATE_PATTERN = "dd-MM-yyyy";

    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withLocale(Locale.getDefault())
                .withZone(ZoneOffset.UTC);
    }
}
