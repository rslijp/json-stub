package model.attorney;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.model.attorneys.BankAccountNumber;
import nl.rabobank.powerofattorney.model.ids.AccountId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BankAccountNumberTest {

    @SneakyThrows
    @Test
    public void should_serialize_bank_account_number(){
        var id = new BankAccountNumber("NL23RABO123123123");

        var raw = new ObjectMapper().writeValueAsString(id);

       assertThat(raw, is("\"NL23RABO123123123\""));
    }

    @SneakyThrows
    @Test
    public void should_deserialize_id_null_value(){
        var raw = "null";

        var id = new ObjectMapper().readValue(raw, BankAccountNumber.class);

        assertThat(id, nullValue());
    }

    @SneakyThrows
    @Test
    public void should_deserialize_bank_acount_number(){
        var raw = "\"NL23RABO123123123\"";

        var id = new ObjectMapper().readValue(raw, BankAccountNumber.class);

        assertThat(id.getValue(), is("NL23RABO123123123"));
    }


    @SneakyThrows
    @Test
    public void should_reject_null_id(){
        try {
            new BankAccountNumber(null);
            fail();
        } catch(NullPointerException e){
           assertThat(e.getMessage(), is("iban is marked non-null but is null"));
        }
    }

    @SneakyThrows
    @Test
    public void should_parse_bank_account_in_parts(){
        var account = new BankAccountNumber("NL23RABO123123123");

        
        assertThat(account.getCountyCode(), is("NL"));
        assertThat(account.getCheckSum(), is("23"));
        assertThat(account.getBank(), is("RABO"));
        assertThat(account.getAccountNumber(), is("123123123"));
    }

    @SneakyThrows
    @Test
    public void should_return_id_from_bank_account(){
        var account = new BankAccountNumber("NL23RABO123123123");


        assertThat(account.asId(), is(new AccountId("123123123")));
    }
}
