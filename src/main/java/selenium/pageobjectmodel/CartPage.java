package selenium.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableabstractcomponents.AbstractComponents;

public class CartPage  extends AbstractComponents{

	WebDriver driver;
	CheckOutPage checkoutPage;
	ProductCatalogue productCatalogue;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	private WebElement checkOutButton;
	
	
	
	//Methods	
	public Boolean VerifyProductsOnCart(String productName) {
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equals(productName));
		return match;
		
	}
	
	public CheckOutPage Checkout()
	{
		checkOutButton.click();
		checkoutPage = new CheckOutPage(driver);
		return checkoutPage;
	}
}
