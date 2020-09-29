package model.card.impl;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.cards.CreditCard;
import nl.rabobank.powerofattorney.model.cards.DebitCard;
import nl.rabobank.powerofattorney.model.cards.Limit;
import nl.rabobank.powerofattorney.model.cards.impl.JsonStubCardRepository;
import nl.rabobank.powerofattorney.model.enums.*;
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
public class CardRepositoryTest
{
    @TestConfiguration
    static class  CardRepositoryTestConfiguration {

        @Bean
        public JsonStubCardRepository jsonStubPowerOfAttorneyRepository() {
            return new JsonStubCardRepository(new ApiClient());
        }
    }

    @BeforeClass
    public static void setUp()
    {
        JsonStubUtil.start();
    }

    @Autowired
    private JsonStubCardRepository repository;

    @Test
    public void get_for_0001_should_return_the_debit_card() {
        var debitCard = repository.debitCard(new CardId("1111"));

        assertThat(debitCard, is(
                new DebitCard(
                        new CardId("1111"),
                        CardStatus.ACTIVE,
                        1234,
                        5,
                        "Frodo Basggins",
                        new Limit(
                                3000,
                                PeriodUnit.PER_WEEK
                        ),
                        new Limit(
                                50,
                                PeriodUnit.PER_MONTH
                        ),
                        true

                )));
    }

    @Test
    public void debit_card_for_0666_should_throw_not_found_exception() {
        try {
            repository.debitCard(new CardId("0666"));
            fail();
        } catch (Exception e){
            assertThat(e.getMessage(), is("Not found in debit-cards"));
        }
    }


    @Test
    public void credit_for_0001_should_return_the_debit_card() {
        var creditCard = repository.creditCard(new CardId("3333"));

        assertThat(creditCard, is(
                new CreditCard(
                        new CardId("3333"),
                        CardStatus.ACTIVE,
                        5075,
                        1,
                        "Boromir",
                        3000

                )));
    }

    @Test
    public void credit_card_for_0666_should_throw_not_found_exception() {
        try {
            repository.creditCard(new CardId("0666"));
            fail();
        } catch (Exception e){
            assertThat(e.getMessage(), is("Not found in credit-cards"));
        }
    }
}
