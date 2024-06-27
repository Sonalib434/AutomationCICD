package selenium.pageobjectmodel.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.pageobjectmodel.CartPage;
import selenium.pageobjectmodel.CheckOutPage;
import selenium.pageobjectmodel.ConfirmationPage;
import selenium.pageobjectmodel.LandingPage;
import selenium.pageobjectmodel.OrderPage;
import selenium.pageobjectmodel.ProductCatalogue;
import selenium.pageobjectmodel.TestComponents.BaseClass;
import selenium.reusableabstractcomponents.RetryExecution;

public class SubmitOrderPOM extends BaseClass{

	WebDriver driver;
	// region Inputs
	
	  //String username = "contact.sonali@gmail.com"; 
	  //String password = "sonaliDemo123"; 
	  //String productName ="ZARA COAT 3";
	 
	// end region	
	
	@Test(dataProvider = "getData", groups="Purchase")
	public void PlaceOrder(HashMap<String, String> data) throws IOException {
				
		String username = data.get("username"); 
		String password = data.get("password");
	    String productName = data.get("productName");
	    String countryName = "India";
		  
		LandingPage landingPage = LaunchApplication();		
		//login
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);		
		//All Products fetching
		productCatalogue.getProductList();		
		//Add product to Cart
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.ShowCartPage();
		//verify Products on cart
		Boolean match = cartPage.VerifyProductsOnCart(productName);
		Assert.assertTrue(match);		
		//checkout
		CheckOutPage checkoutPage = cartPage.Checkout();
		checkoutPage.SelectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.SubmitOrder();
		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		//CloseApplication();
	}
	
	@Test(dependsOnMethods = {"PlaceOrder"}, retryAnalyzer = RetryExecution.class)
	public void OrderHistoryCheck(String username, String password, String productName) throws IOException
	{
		LandingPage landingPage = LaunchApplication();		
		//login
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);			
		OrderPage orderPage = productCatalogue.ShowOrderPage();
		//verify Products on order summary
		Boolean match = orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(match);	
	}
	
	@DataProvider
	public Object[][]  getData()
	{
		//return new Object[][] {{"contact.sonali@gmail.com", "sonaliDemo123","ZARA COAT 3"},{"contact.sonali@gmail.com", "sonaliDemo123","ADIDAS ORIGINAL"}};
		
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("username", "contact.sonali@gmail.com");
		map1.put("password", "sonaliDemo123");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		map2.put("username", "contact.sonali@gmail.com");
		map2.put("password", "sonaliDemo123");
		map2.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map1},{map2}};
		
	}
	
	
	
	//Extent Reports -
}
