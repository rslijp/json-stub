package stub;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import nl.rabobank.powerofattorney.model.attorney.Attorney;
import nl.rabobank.powerofattorney.model.ids.AttorneyId;
import nl.rabobank.powerofattorney.model.attorney.AttorneySummary;
import nl.rabobank.powerofattorney.model.cards.*;
import nl.rabobank.powerofattorney.model.enums.*;
import nl.rabobank.powerofattorney.model.ids.CardId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class PowerOfAttorneyTest extends RestAssuredTestBase
{
    @Test
    public void get_of_power_of_attorneys_should_return_all_four_results() {
        var attorneyIds = given().
        when().
            get("/power-of-attorneys").
        then().
            statusCode(200).
        and().extract().as(AttorneySummary[].class);

        assertThat(Arrays.asList(attorneyIds), contains(
                new AttorneySummary(new AttorneyId("0001")),
                new AttorneySummary(new AttorneyId("0002")),
                new AttorneySummary(new AttorneyId("0003")),
                new AttorneySummary(new AttorneyId("0004"))
        ));
    }

    @Test
    public void get_of_power_of_attorneys_for_0001_should_return_result_for_0001() {
        var attorney = given().
        when().
            get("/power-of-attorneys/0001").
        then().
            statusCode(200).
        and().
            body("id", is("0001")).
        and().
            extract().as(Attorney.class);

        assertThat(attorney, is(
                new Attorney(
                new AttorneyId("0001"),
                "Super duper company",
                "Fellowship of the ring",
                "NL23RABO123456789",
                Direction.GIVEN,
                new Authorization[]{
                        Authorization.DEBIT_CARD,
                        Authorization.VIEW,
                        Authorization.PAYMENT
                },
                new CardSummary[]{
                        new CardSummary(
                                new CardId("1111"), CardType.DEBIT_CARD
                        ),
                        new CardSummary(
                                new CardId("2222"), CardType.DEBIT_CARD
                        ),
                        new CardSummary(
                                new CardId("3333"), CardType.CREDIT_CARD
                        )
                }

        )));
    }


    @Test
    public void get_of_power_of_unknown_attorneys_should_result_in_404() {
        given().
        when().
            get("/power-of-attorneys/0666").
        then().
            statusCode(404);
    }

    @Test
    public void get_debit_card_1111_should_return_results_for_card_1111() {
        var debitCard = given().
        when().
            get("/debit-cards/1111").
        then().
            statusCode(200).
        and().
            extract().as(DebitCard.class);

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
    public void get_debit_card_for_unknown_id_should_return_status_404() {
        given().
        when().
            get("/debit-cards/4321").
        then().
            statusCode(404);
    }

    @Test
    public void get_credit_card_3333_should_return_results_for_card_3333() {
        var creditCard = given().
                when().
                get("/credit-cards/3333").
                then().
                statusCode(200).
                and().
                extract().as(CreditCard.class);

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
    public void get_credit_card_for_unknown_id_should_return_status_404() {
        given().
                when().
                get("/debit-cards/4321").
                then().
                statusCode(404);
    }
}
