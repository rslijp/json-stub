package backingapi;

import java.time.Instant;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalField;
import nl.rabobank.powerofattorney.backingapi.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class ConstantTest {

    @Test
    public void should_format(){
        var date = LocalDate.of(2007,10,12);

        var txt = Constant.dateFormatter().format(date);

        assertThat(txt, is("12-10-2007"));

    }

    @Test
    public void should_parse(){
        var txt = "12-10-2007";

        var date = Constant.dateFormatter().parse(txt,  LocalDate::from);

        assertThat(date.getYear(), is(2007));
        assertThat(date.getMonth(), is(Month.OCTOBER));
        assertThat(date.getDayOfMonth(), is(12));

    }
}
