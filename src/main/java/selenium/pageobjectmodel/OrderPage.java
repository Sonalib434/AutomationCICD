package selenium.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableabstractcomponents.AbstractComponents;

public class OrderPage  extends AbstractComponents{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	//Methods	
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product->product.getText().equals(productName));
		return match;
		
	}
	
	
	
}
