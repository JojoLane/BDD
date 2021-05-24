package features;


import Revolut.PaymentService;
import Revolut.Person;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class MyStepdefinitions {
    private Person danny;
    private Person sarah;
    private double topUpAmount;
    PaymentService topUpMethod;
    PaymentService requestService;

    @Before // Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        sarah = new Person("Sarah");
    }


    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
       // System.out.println(startingBalance);
        danny.setAccountBalance(startingBalance, "EUR");
        //throw new io.cucumber.java.PendingException();
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topAmount) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        this.topUpAmount = topAmount;

    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        this.topUpMethod = topUpSource;
        System.out.println(topUpMethod.getType());
    }


    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod.isRequestStatus());
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange


        //Act
        double actualResult = danny.getAccount("EUR").getBalance();

        //Assert
        Assert.assertEquals(newBalance, actualResult, 0);

    }

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOfStartBalance(double startBalance) {
        danny.setAccountBalance(startBalance, "EUR");
    }

    @When("Danny now tops up by {double}")
    public void dannyNowTopsUpByTopUpAmount(double topUpAmount) {
        danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod.isRequestStatus());
    }

    @Given("Danny has insufficient amount in his DebitCard")
    public void dannyHasInsufficientAmountInHisDebitCard() {
        topUpMethod.insufficient();
    }

    @Given("Danny has sufficient amount in his DebitCard")
    public void dannyHasSufficientAmountInHisDebitCard() {
        topUpMethod.sufficient();
    }

    @Then("The balance in his euro account should be {double}")
    public void theBalanceInHisEuroAccountShouldBeNewBalance(double newBalance) {
        double actual = danny.getAccountBalance("EUR");
        Assert.assertEquals(actual, newBalance, 0);
    }

    @When("Danny sends {double} euro to Sarah's account")
    public void dannySendsEuroToSarahSAccount(double amountToSend) {
        danny.getAccount("EUR").sendFunds(sarah, "EUR", amountToSend);
    }

    @Then("The new balance of his euro account should now be {double} and Sarah's account should have be {double}.")
    public void theNewBalanceOfHisEuroAccountShouldNowBeAndSarahSAccountShouldHaveBe(double dansBal, double sarahsBal) {
        double danActBal = danny.getAccountBalance("EUR");
        double sarahsActBal = sarah.getAccountBalance("EUR");
        Assert.assertEquals(danActBal, dansBal, 0);
        Assert.assertEquals(sarahsActBal, sarahsBal, 0);
    }

    @When("Danny exchanges {double} euro to USD with an exchange rate of {double}")
    public void dannyExchangesEuroToUSDWithAnExchangeRateOf(double amount, double rate) {
        danny.getAccount("EUR").exchangeCurrency(danny, "EUR", "USD", rate, amount);
    }


    @Then("The balance in his USD account should be {int} and his euro account should be {int}.")
    public void theBalanceInHisUSDAccountShouldBeAndHisEuroAccountShouldBe(int usdBalance, int euroBalance) {
        double actualUsdBal = danny.getAccountBalance("USD");
        double actualEurBal = danny.getAccountBalance("EUR");

        Assert.assertEquals(actualEurBal, euroBalance, 0);
        Assert.assertEquals(actualUsdBal, usdBalance, 0);

    }


}
