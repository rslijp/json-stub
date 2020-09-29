package service;

import java.time.LocalDate;
import java.util.List;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.accounts.Account;
import nl.rabobank.powerofattorney.model.accounts.impl.JsonStubAccountRepository;
import nl.rabobank.powerofattorney.model.attorneys.BankAccountNumber;
import nl.rabobank.powerofattorney.model.attorneys.CardReference;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.attorneys.impl.JsonStubPowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.cards.CreditCard;
import nl.rabobank.powerofattorney.model.cards.DebitCard;
import nl.rabobank.powerofattorney.model.cards.Limit;
import nl.rabobank.powerofattorney.model.cards.impl.JsonStubCardRepository;
import nl.rabobank.powerofattorney.model.enums.*;
import nl.rabobank.powerofattorney.model.ids.AccountId;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import nl.rabobank.powerofattorney.model.ids.CardId;
import nl.rabobank.powerofattorney.service.PowerOfAttorneyAggregate;
import nl.rabobank.powerofattorney.service.PowerOfAttorneyAggregateService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import util.JsonStubUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
public class PowerOfAttorneyAggregateServiceTest
{
    @TestConfiguration
    static class  CardRepositoryTestConfiguration {

        @Bean
        public JsonStubPowerOfAttorneyRepository jsonStubPowerOfAttorneyRepository() {
            return new JsonStubPowerOfAttorneyRepository(new ApiClient());
        }

        @Bean
        public JsonStubCardRepository jsonStubCardRepository() {
            return new JsonStubCardRepository(new ApiClient());
        }

        @Bean
        public JsonStubAccountRepository jsonStubAccountRepository() {
            return new JsonStubAccountRepository(new ApiClient());
        }

        @Bean
        public PowerOfAttorneyAggregateService powerOfAttorneyAggregateService(
                JsonStubPowerOfAttorneyRepository poaRepo,
                JsonStubCardRepository cardRepo,
                JsonStubAccountRepository accountRepo
        ) {
            return new PowerOfAttorneyAggregateService(
                    accountRepo,
                    cardRepo,
                    poaRepo
            );
        }
    }

    @BeforeClass
    public static void setUp()
    {
        JsonStubUtil.start();
    }

    @Autowired
    private PowerOfAttorneyAggregateService service;

    @Test
    public void get_for_0001_should_return_aggregate_record() {
        var attorney = service.aggregate(new AttorneyId("0001")).get();

        compare0001(attorney);
    }


    @Test
    public void search_should_return_aggregate_ring_of_the_fellow_ship() {
        var attorneys = service.search("Fellowship of the ring");
        assertThat(attorneys.size(), is(1));
        compare0001(attorneys.get(0));
    }

    private void compare0001(PowerOfAttorneyAggregate attorney) {
        assertThat(attorney, is(
                new PowerOfAttorneyAggregate(
                        new AttorneyId("0001"),
                        "Super duper company",
                        "Fellowship of the ring",
                        new BankAccountNumber("NL23RABO123456789"),
                        new Account(
                                "Super duper company",
                                750,
                                LocalDate.of(2007, 10, 12),
                                null
                        ),
                        Direction.GIVEN,
                        List.of(
                                Authorization.DEBIT_CARD,
                                Authorization.VIEW,
                                Authorization.PAYMENT
                        ),
                        List.of(new DebitCard(
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
                                ),
                                new DebitCard(
                                        new CardId("2222"),
                                        CardStatus.ACTIVE,
                                        6527,
                                        1,
                                        "Aragorn",
                                        new Limit(
                                                100,
                                                PeriodUnit.PER_DAY
                                        ),
                                        new Limit(
                                                10000,
                                                PeriodUnit.PER_MONTH
                                        ),
                                        true
                                )
                        ),
                        List.of(new CreditCard(
                                        new CardId("3333"),
                                        CardStatus.ACTIVE,
                                        5075,
                                        1,
                                        "Boromir",
                                        3000
                                )
                        )

                )));
    }


    @Test
    public void get_for_0002_should_return_aggregate_record() {
        var attorney = service.aggregate(new AttorneyId("0002"));

        assertThat(attorney.isPresent(), is(false));
    }
}
