package qumu.baseclass;

import qumu.api.ApiClient;

import qumu.pages.CartPage;
import qumu.pages.CheckoutOverviewPage;
import qumu.pages.CheckoutPage;
import qumu.pages.HomePage;
import qumu.pages.InventoryPage;
import qumu.pages.LoginPage;

public class PageObjectFactory {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static InventoryPage inventoryPage;
    public static CartPage cartPage;
    public static CheckoutPage checkoutPage;
    public static CheckoutOverviewPage checkoutOverviewPage;
    public static ApiClient apiClient;

    public PageObjectFactory() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        apiClient = new ApiClient();
    }
}
