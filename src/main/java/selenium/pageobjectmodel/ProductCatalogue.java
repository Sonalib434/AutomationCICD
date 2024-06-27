package selenium.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableabstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;
	CartPage cartPage;
	OrderPage orderPage;
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver,this);
	}

	
	//PageFactory	
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	
	
	private By productsByLocator = By.cssSelector(".mb-3");
	private By addToCartByLocator = By.cssSelector("button:last-of-type");
	private By productAddedMsgByLocator = By.id("toast-container");
	private By loadingByLocator = By.cssSelector(".ng-animating");
	private By cartByLocator = By.cssSelector("button[routerlink*='cart']");
	
	//Methods
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsByLocator);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartByLocator).click();
		
		waitForElementToAppear(productAddedMsgByLocator);
		waitForElementToDisappear(loadingByLocator);
		waitForElementToAppear(cartByLocator);		
	}
	
	public CartPage ShowCartPage()
	{
		goToCartPage();
		cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage ShowOrderPage()
	{
		goToOrderPage();
		orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
	
