package model.accounts.impl;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import nl.rabobank.powerofattorney.backingapi.ApiClient;
import nl.rabobank.powerofattorney.model.accounts.Account;
import nl.rabobank.powerofattorney.model.accounts.AccountReference;
import nl.rabobank.powerofattorney.model.accounts.AccountRepository;
import nl.rabobank.powerofattorney.model.accounts.impl.JsonStubAccountRepository;
import nl.rabobank.powerofattorney.model.attorneys.CardReference;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorneyReference;
import nl.rabobank.powerofattorney.model.attorneys.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.attorneys.impl.JsonStubPowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.model.enums.Authorization;
import nl.rabobank.powerofattorney.model.enums.CardType;
import nl.rabobank.powerofattorney.model.enums.Direction;
import nl.rabobank.powerofattorney.model.ids.AccountId;
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
public class AccountRepositoryTest
{

    @TestConfiguration
    static class  AccountRepositoryTestConfiguration {

        @Bean
        public AccountRepository jsonStubPowerOfAttorneyRepository() {
            return new JsonStubAccountRepository(new ApiClient());
        }
    }

    @BeforeClass
    public static void setUp()
    {
        JsonStubUtil.start();
    }

    @Autowired
    private AccountRepository repository;


    @Test
    public void get_for_123456789_should_return_whole_record() {
        var account = repository.get(new AccountId("123456789"));

        assertThat(account, is(
                new Account(
                        "Super duper company",
                        750,
                        LocalDate.of(2007, 10,12),
                        null

                )));
    }

    @Test
    public void get_for_0666_should_throw_not_found_exception() {
        try {
            repository.get(new AccountId("0666"));
            fail();
        } catch (Exception e){
            assertThat(e.getMessage(), is("Not found in accounts"));
        }
    }


}
