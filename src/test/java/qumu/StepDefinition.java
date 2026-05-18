package qumu;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class StepDefinition {

    // Launch website

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() {

        iniClass.homePage.launchApplication();
        
        Log.logger.info("Application launched successfully");

    }

    // Login

    @And("^I login in with the following details$")
    public void iLoginInWithTheFollowingDetails(DataTable dt) {

    	List<List<String>> data = dt.asLists(String.class);

        String username = data.get(1).get(0);

        String password = data.get(1).get(1);

        iniClass.loginPage.loginToApplication(
                username,
                password);
        Log.logger.info("Login completed successfully");

    }

    // Add items to basket

    @And("^I add the following items to the basket$")
    public void iAddTheFollowingItemsToTheBasket(DataTable dt) {

        List<String> items = dt.asList(String.class);

        for (int i = 0; i < items.size(); i++) {

            iniClass.inventoryPage.addItemToCart(items.get(i));

        }
        Log.logger.info("All items added successfully");
    }

    // Verify cart count

    @And("^I should see (\\d+) items added to the shopping cart$")
    public void iShouldSeeItemsAddedToTheShoppingCart(int expectedCount) {

        String actualCount =
                iniClass.inventoryPage.getCartBadgeCount();

        Assert.assertEquals(
                actualCount,
                String.valueOf(expectedCount));
        Log.logger.info("Cart item count validation successful");

    }

    // Click shopping cart
    
    @And("^I click on the shopping cart$")
    public void iClickOnTheShoppingCart() {

        iniClass.inventoryPage.clickCartIcon();

    }

    // Verify quantity

    @And("^I verify that the QTY count for each item should be 1$")
    public void iVerifyThatTheQTYCountForEachItemShouldBe1() {

        Assert.assertTrue(
                iniClass.cartPage.verifyAllItemQuantityIsOne());
        Log.logger.info("All item quantities validated successfully");

    }

    // Remove item

    @And("^I remove the following item:$")
    public void iRemoveTheFollowingItem(DataTable dt) {

        List<String> items = dt.asList(String.class);

        iniClass.cartPage.removeItem(items.get(0));
        
        Log.logger.info("Item removed successfully");

    }

    // Click checkout

    @And("^I click on the CHECKOUT button$")
    public void iClickOnTheCHECKOUTButton() {

        iniClass.cartPage.clickCheckoutButton();

    }

    // Enter first name

    @And("^I type \"([^\"]*)\" for First Name$")
    public void iTypeForFirstName(String firstName) {

        iniClass.checkoutPage.enterFirstName(firstName);

    }

    // Enter last name

    @And("^I type \"([^\"]*)\" for Last Name$")
    public void iTypeForLastName(String lastName) {

        iniClass.checkoutPage.enterLastName(lastName);

    }

    // Enter zip code

    @And("^I type \"([^\"]*)\" for ZIP/Postal Code$")
    public void iTypeForZIPPostalCode(String zipCode) {

        iniClass.checkoutPage.enterZipCode(zipCode);

    }

    // Click continue

    @When("^I click on the CONTINUE button$")
    public void iClickOnTheCONTINUEButton() {

        iniClass.checkoutPage.clickContinueButton();

    }

    // Verify item total

    @Then("^Item total will be equal to the total of items on the list$")
    public void itemTotalWillBeEqualToTheTotalOfItemsOnTheList() {

        double calculatedTotal =
                iniClass.checkoutOverviewPage.calculateItemTotal();

        double displayedTotal =
                iniClass.checkoutOverviewPage.getDisplayedItemTotal();

        Assert.assertEquals(
                displayedTotal,
                calculatedTotal);
        
        Log.logger.info("Item total validation successful");

    }

    // Verify tax

    @And("^a Tax rate of 8 % is applied to the total$")
    public void aTaxRateOf8IsAppliedToTheTotal() {
    	Log.logger.info("Validating Tax Calculation");
    	
        double total =
                iniClass.checkoutOverviewPage.calculateItemTotal();

        double expectedTax = total * 0.08;

        double displayedTax =
                iniClass.checkoutOverviewPage.getDisplayedTax();

        Assert.assertEquals(
                Math.round(displayedTax * 100.0) / 100.0,
                Math.round(expectedTax * 100.0) / 100.0);
        
        Log.logger.info("Tax validation successful");

    }
 
    // API automation start


    @Given("^I get the default list of users for on 1st page$")
    public void iGetTheDefaultListofusers() {

        iniClass.apiPage.getUsersPageOne();
    }

    @When("^I get the list of all users within every page$")
    public void iGetTheListOfAllUsers() {

        iniClass.apiPage.getAllUsers();
    }

    @Then("^I should see total users count equals the number of user ids$")
    public void iShouldMatchTotalCount() {

        int total =
                ApiPage.response.jsonPath().getInt("per_page");

        List<Integer> ids =
                ApiPage.response.jsonPath().getList("data.id");

        Assert.assertEquals(ids.size(), total);
    }

    @Given("^I make a search for user (.*)$")
    public void iMakeASearchForUser(int userId) {

        iniClass.apiPage.getSingleUser(userId);
    }

    @Then("^I should see the following user data$")
    public void IShouldSeeFollowingUserData(DataTable dt) {

        List<Map<String, String>> data =
                dt.asMaps(String.class, String.class);

        String expectedFirstName =
                data.get(0).get("first_name");

        String expectedEmail =
                data.get(0).get("email");

        String actualFirstName =
                ApiPage.response.jsonPath().getString("data.first_name");

        String actualEmail =
                ApiPage.response.jsonPath().getString("data.email");

        Assert.assertEquals(actualFirstName, expectedFirstName);

        Assert.assertEquals(actualEmail, expectedEmail);
    }

    @Then("^I receive error code (.*) in response$")
    public void iReceiveErrorCodeInResponse(int responseCode) {

        Assert.assertEquals(
                ApiPage.response.getStatusCode(),
                responseCode);
    }

    @Given("^I create a user with following (.*) (.*)$")
    public void iCreateUserWithFollowing(String name, String job) {

        iniClass.apiPage.createUser(name, job);
    }

    @Then("^response should contain the following data$")
    public void iReceiveResponseShouldContainFollowingData(DataTable dt) {

        Assert.assertNotNull(
                ApiPage.response.jsonPath().getString("id"));

        Assert.assertNotNull(
                ApiPage.response.jsonPath().getString("createdAt"));
    }

    @Given("^I login unsuccessfully with the following data$")
    public void iLoginSuccesfullyWithFollowingData(DataTable dt) {

        List<Map<String, String>> data =
                dt.asMaps(String.class, String.class);

        String email =
                data.get(0).get("Email");

        String password =
                data.get(0).get("Password");

        iniClass.apiPage.loginUser(email, password);
    }

    @Given("^I wait for the user list to load$")
    public void iWaitForUserListToLoad() {

        iniClass.apiPage.delayedResponse();
    }

    @Then("^I should see that every user has a unique id$")
    public void iShouldSeeThatEveryUserHasAUniqueID() {

        Assert.assertTrue(
                iniClass.apiPage.verifyUniqueIds());
    }

    @Then("^I should get a response code of (\\d+)$")
    public void iShouldGetAResponseCodeOf(int responseCode) {

        Assert.assertEquals(
                ApiPage.response.getStatusCode(),
                responseCode);
    }

    @And("^I should see the following response message:$")
    public void iShouldSeeTheFollowingResponseMessage(DataTable dt) {

        String actualResponse =
                ApiPage.response.getBody().asString();

        Assert.assertTrue(
                actualResponse.contains("Missing password"));
    }

}