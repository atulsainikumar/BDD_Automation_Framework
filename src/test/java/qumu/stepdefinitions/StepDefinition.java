package qumu.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import qumu.api.ApiClient;
import qumu.baseclass.PageObjectFactory;
import qumu.utils.LoadProp;
import qumu.utils.Log;

import java.util.List;
import java.util.Map;

public class StepDefinition {

    // ─────────────────────────────────────────
    // UI — Launch
    // ─────────────────────────────────────────

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {
        PageObjectFactory.homePage.launchApplication();
        Log.logger.info("Application launched successfully");
    }

    // ─────────────────────────────────────────
    // UI — Login (credentials from config.properties)
    // ─────────────────────────────────────────

    @And("^I login with valid credentials$")
    public void iLoginWithValidCredentials() {
        PageObjectFactory.loginPage.loginToApplication(
                LoadProp.getProperty("uiUsername"),
                LoadProp.getProperty("uiPassword"));
    }

    // ─────────────────────────────────────────
    // UI — Cart interactions
    // ─────────────────────────────────────────

    @And("^I add the following items to the basket$")
    public void iAddTheFollowingItemsToTheBasket(DataTable dt) {
        List<String> items = dt.asList(String.class);
        for (String item : items) {
            PageObjectFactory.inventoryPage.addItemToCart(item);
        }
        Log.logger.info("All items added successfully");
    }

    @And("^I should see (\\d+) items added to the shopping cart$")
    public void iShouldSeeItemsAddedToTheShoppingCart(int expectedCount) {
        String actualCount = PageObjectFactory.inventoryPage.getCartBadgeCount();
        Assert.assertEquals(actualCount, String.valueOf(expectedCount));
        Log.logger.info("Cart item count validation successful");
    }

    @And("^I click on the shopping cart$")
    public void iClickOnTheShoppingCart() {
        PageObjectFactory.inventoryPage.clickCartIcon();
    }

    @And("^I verify that the QTY count for each item should be 1$")
    public void iVerifyThatTheQTYCountForEachItemShouldBe1() {
        Assert.assertTrue(PageObjectFactory.cartPage.verifyAllItemQuantityIsOne());
        Log.logger.info("All item quantities validated successfully");
    }

    @And("^I remove the following item:$")
    public void iRemoveTheFollowingItem(DataTable dt) {
        List<String> items = dt.asList(String.class);
        PageObjectFactory.cartPage.removeItem(items.get(0));
        Log.logger.info("Item removed successfully");
    }

    @And("^I click on the CHECKOUT button$")
    public void iClickOnTheCHECKOUTButton() {
        PageObjectFactory.cartPage.clickCheckoutButton();
    }

    // ─────────────────────────────────────────
    // UI — Checkout form
    // ─────────────────────────────────────────

    @And("^I type \"([^\"]*)\" for First Name$")
    public void iTypeForFirstName(String firstName) {
        PageObjectFactory.checkoutPage.enterFirstName(firstName);
    }

    @And("^I type \"([^\"]*)\" for Last Name$")
    public void iTypeForLastName(String lastName) {
        PageObjectFactory.checkoutPage.enterLastName(lastName);
    }

    @And("^I type \"([^\"]*)\" for ZIP/Postal Code$")
    public void iTypeForZIPPostalCode(String zipCode) {
        PageObjectFactory.checkoutPage.enterZipCode(zipCode);
    }

    @When("^I click on the CONTINUE button$")
    public void iClickOnTheCONTINUEButton() {
        PageObjectFactory.checkoutPage.clickContinueButton();
    }

    // ─────────────────────────────────────────
    // UI — Checkout assertions
    // ─────────────────────────────────────────

    @Then("^Item total will be equal to the total of items on the list$")
    public void itemTotalWillBeEqualToTheTotalOfItemsOnTheList() {
        double calculatedTotal = PageObjectFactory.checkoutOverviewPage.calculateItemTotal();
        double displayedTotal = PageObjectFactory.checkoutOverviewPage.getDisplayedItemTotal();
        Assert.assertEquals(displayedTotal, calculatedTotal);
        Log.logger.info("Item total validation successful");
    }

    @And("^a Tax rate of 8 % is applied to the total$")
    public void aTaxRateOf8IsAppliedToTheTotal() {
        Log.logger.info("Validating tax calculation");
        double total = PageObjectFactory.checkoutOverviewPage.calculateItemTotal();
        double expectedTax = total * 0.08;
        double displayedTax = PageObjectFactory.checkoutOverviewPage.getDisplayedTax();
        Assert.assertEquals(
                Math.round(displayedTax * 100.0) / 100.0,
                Math.round(expectedTax * 100.0) / 100.0);
        Log.logger.info("Tax validation successful");
    }

    // ─────────────────────────────────────────
    // API — LIST USERS (paginated)
    // ─────────────────────────────────────────

    @Given("^I request the user list and note the total count$")
    public void iRequestUserListAndNoteTotalCount() {
        PageObjectFactory.apiClient.fetchUserListAndNoteTotalCount();
    }

    @When("^I collect users across all pages$")
    public void iCollectUsersAcrossAllPages() {
        PageObjectFactory.apiClient.collectUsersAcrossAllPages();
    }

    @Then("^the number of collected user ids should equal the total count$")
    public void theCollectedUserIdsShouldEqualTotalCount() {
        Assert.assertTrue(PageObjectFactory.apiClient.collectedCountMatchesDeclaredTotal());
    }

    // ─────────────────────────────────────────
    // API — SINGLE USER
    // ─────────────────────────────────────────

    @Given("^I make a search for user (.*)$")
    public void iMakeASearchForUser(int userId) {
        PageObjectFactory.apiClient.getSingleUser(userId);
    }

    @Then("^I should see the following user data$")
    public void iShouldSeeFollowingUserData(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class, String.class);
        String expectedFirstName = data.get(0).get("first_name");
        String expectedEmail = data.get(0).get("email");
        String actualFirstName = ApiClient.response.jsonPath().getString("data.first_name");
        String actualEmail = ApiClient.response.jsonPath().getString("data.email");
        Assert.assertEquals(actualFirstName, expectedFirstName);
        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Then("^I receive error code (.*) in response$")
    public void iReceiveErrorCodeInResponse(int responseCode) {
        Assert.assertEquals(ApiClient.response.getStatusCode(), responseCode);
    }

    // ─────────────────────────────────────────
    // API — CREATE USER
    // ─────────────────────────────────────────

    @Given("^I create a user with following (.*) (.*)$")
    public void iCreateUserWithFollowing(String name, String job) {
        PageObjectFactory.apiClient.createUser(name, job);
    }

    @Then("^response should contain the following data$")
    public void responseShouldContainFollowingData(DataTable dt) {
        // dt.row(0) reads the first row as a flat List<String> regardless of column count,
        // which is what we need for a single-header-row multi-column table.
        List<String> fields = dt.row(0);
        for (String field : fields) {
            Assert.assertNotNull(
                    ApiClient.response.jsonPath().getString(field),
                    "Expected field '" + field + "' to be present in the response but was null");
        }
    }

    // ─────────────────────────────────────────
    // API — LOGIN
    // ─────────────────────────────────────────

    @Given("^I send a login request with valid credentials$")
    public void iSendLoginRequestWithValidCredentials() {
        PageObjectFactory.apiClient.loginWithValidCredentials();
    }

    @Given("^I send a login request with missing password$")
    public void iSendLoginRequestWithMissingPassword() {
        PageObjectFactory.apiClient.loginWithMissingPassword();
    }

    @Then("^I should get a response code of (\\d+)$")
    public void iShouldGetAResponseCodeOf(int responseCode) {
        Assert.assertEquals(ApiClient.response.getStatusCode(), responseCode);
    }

    @And("^I should see the following response message:$")
    public void iShouldSeeTheFollowingResponseMessage(DataTable dt) {
        String actualResponse = ApiClient.response.getBody().asString();
        Assert.assertTrue(actualResponse.contains("Missing password"));
    }

    // ─────────────────────────────────────────
    // API — DELAYED RESPONSE
    // ─────────────────────────────────────────

    @Given("^I wait for the user list to load$")
    public void iWaitForUserListToLoad() {
        PageObjectFactory.apiClient.delayedResponse();
    }

    @Then("^I should see that every user has a unique id$")
    public void iShouldSeeThatEveryUserHasAUniqueID() {
        Assert.assertTrue(PageObjectFactory.apiClient.verifyUniqueIds());
    }
}
