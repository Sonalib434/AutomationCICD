package selenium.pageobjectmodel.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.pageobjectmodel.CartPage;
import selenium.pageobjectmodel.ProductCatalogue;
import selenium.pageobjectmodel.TestComponents.BaseClass;

public class ErrorValidation extends BaseClass{
	
	
	@Test(groups = {"Validation"})
	public void LoginErrorValidation() throws IOException
	{
		String username = "contact.sona@gmail.com";
		String password = "sonaliDemo";
		
		landingPage.loginApplication(username, password);
		
		//xpath - div[@class='ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		//css- .ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		//js -document.querySelector("div[aria-label='Incorrect email or password.']")
		
		String errMsg1 = landingPage.getErrorMessage();
		System.out.println(errMsg1);
		Assert.assertEquals("Incorrect email password.",errMsg1);
		
	}
	
	@Test(groups = {"Error"})
	public void ProductErrorValidation() throws IOException {
						
		// region Inputs
		String username = "contact.sonali@gmail.com";
		String password = "sonaliDemo123";
		String productName ="ZARA COAT 3";
		// end region
		
		//login
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
		//All Products fetching
		productCatalogue.getProductList();		
		//Add product to Cart
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.ShowCartPage();
		//verify Products on cart
		Boolean match = cartPage.VerifyProductsOnCart("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
