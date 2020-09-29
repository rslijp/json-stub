package model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AttorneyIdTest {

    @SneakyThrows
    @Test
    public void should_serialize_id(){
        var id = new AttorneyId("42");

        var raw = new ObjectMapper().writeValueAsString(id);

       assertThat(raw, is("\"42\""));
    }

    @SneakyThrows
    @Test
    public void should_deserialize_id_null_value(){
        var raw = "null";

        var id = new ObjectMapper().readValue(raw, AttorneyId.class);

        assertThat(id, nullValue());
    }

    @SneakyThrows
    @Test
    public void should_deserialize_id(){
        var raw = "\"42\"";

        var id = new ObjectMapper().readValue(raw, AttorneyId.class);

        assertThat(id.getValue(), is("42"));
    }


    @SneakyThrows
    @Test
    public void should_reject_null_id(){
        try {
            new AttorneyId(null);
            fail();
        } catch(NullPointerException e){
           assertThat(e.getMessage(), is("id is marked non-null but is null"));
        }
    }

}
