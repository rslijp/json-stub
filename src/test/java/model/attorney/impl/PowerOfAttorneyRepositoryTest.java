package model.attorney.impl;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.attorneys.*;
import nl.rabobank.powerofattorney.model.attorneys.impl.JsonStubPowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.CardType;
import nl.rabobank.powerofattorney.model.enums.Direction;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import nl.rabobank.powerofattorney.model.ids.CardId;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import util.JsonStubUtil;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
public class PowerOfAttorneyRepositoryTest
{

    @TestConfiguration
    static class  PowerOfAttorneyRepositoryTestConfiguration {

        @Bean
        public PowerOfAttorneyRepository jsonStubPowerOfAttorneyRepository() {
            return new JsonStubPowerOfAttorneyRepository(new ApiClient());
        }
    }

    @BeforeClass
    public static void setUp()
    {
        JsonStubUtil.start();
    }

    @Autowired
    private PowerOfAttorneyRepository repository;

    @Test
    public void get_of_power_of_attorneys_should_return_all_four_results() {
        var attorneyIds = repository.all();

        assertThat(Arrays.asList(attorneyIds), contains(
                new PowerOfAttorneyReference(new AttorneyId("0001")),
                new PowerOfAttorneyReference(new AttorneyId("0002")),
                new PowerOfAttorneyReference(new AttorneyId("0003")),
                new PowerOfAttorneyReference(new AttorneyId("0004"))
        ));
    }

    @Test
    public void get_for_0001_should_return_all_record() {
        var attorney = repository.get(new AttorneyId("0001"));

        assertThat(attorney, is(
                new PowerOfAttorney(
                        new AttorneyId("0001"),
                        "Super duper company",
                        "Fellowship of the ring",
                        new BankAccountNumber("NL23RABO123456789"),
                        Direction.GIVEN,
                        new Authorization[]{
                                Authorization.DEBIT_CARD,
                                Authorization.VIEW,
                                Authorization.PAYMENT
                        },
                        new CardReference[]{
                                new CardReference(
                                        new CardId("1111"), CardType.DEBIT_CARD
                                ),
                                new CardReference(
                                        new CardId("2222"), CardType.DEBIT_CARD
                                ),
                                new CardReference(
                                        new CardId("3333"), CardType.CREDIT_CARD
                                )
                        }

                )));
    }

    @Test
    public void get_for_0666_should_throw_not_found_exception() {
        try {
            repository.get(new AttorneyId("0666"));
            fail();
        } catch (Exception e){
            assertThat(e.getMessage(), is("Not found in power-of-attorneys"));
        }
    }

    @Test
    public void should_detect_api_down() {
        try {
            new JsonStubPowerOfAttorneyRepository(new ApiClient("http://localhost:666")).get(new AttorneyId("0666"));
            fail();
        } catch (Exception e){
            assertThat(e.getMessage(), is("I/O Error during stub api call"));
        }
    }

}
