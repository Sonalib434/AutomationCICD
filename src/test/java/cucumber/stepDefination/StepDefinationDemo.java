package cucumber.stepDefination;

import java.io.IOException;
import org.testng.Assert;

import io.cucumber.java.en.*;
import selenium.pageobjectmodel.CartPage;
import selenium.pageobjectmodel.CheckOutPage;
import selenium.pageobjectmodel.ConfirmationPage;
import selenium.pageobjectmodel.LandingPage;
import selenium.pageobjectmodel.ProductCatalogue;
import selenium.pageobjectmodel.TestComponents.BaseClass;

public class StepDefinationDemo extends BaseClass {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckOutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecomerce Page")
	public void I_landed_on_Ecomerce_Page() throws IOException {
		landingPage = LaunchApplication();
	}

	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add (.+) from Cart$")
	public void I_add_productName_from_Cart(String productName) {
		// All Products fetching
		productCatalogue.getProductList();
		// Add product to Cart
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_productName_and_submit_the_order(String productName) {
		cartPage = productCatalogue.ShowCartPage();
		// verify Products on cart
		Boolean match = cartPage.VerifyProductsOnCart(productName);
		Assert.assertTrue(match);
		// checkout
		checkoutPage = cartPage.Checkout();
		checkoutPage.SelectCountry("India");
		confirmationPage = checkoutPage.SubmitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		String errMsg1 = landingPage.getErrorMessage();
		System.out.println(errMsg1);
		Assert.assertEquals(string,errMsg1);
		driver.close();
	}
}
