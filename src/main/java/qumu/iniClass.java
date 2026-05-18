package qumu;

public class iniClass extends BasePage {

    public static HomePage homePage;

    public static LoginPage loginPage;

    public static InventoryPage inventoryPage;

    public static CartPage cartPage;

    public static CheckoutPage checkoutPage;

    public static CheckoutOverviewPage checkoutOverviewPage;
    
    public static ApiPage apiPage;

    // =========================
    // Constructor
    // =========================

    public iniClass() {

        homePage = new HomePage();

        loginPage = new LoginPage();

        inventoryPage = new InventoryPage();

        cartPage = new CartPage();

        checkoutPage = new CheckoutPage();

        checkoutOverviewPage = new CheckoutOverviewPage();
        
        apiPage = new ApiPage();

    }

}