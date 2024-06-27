package selenium.pageobjectmodel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableabstractcomponents.AbstractComponents;

public class CheckOutPage  extends AbstractComponents{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver,this);
	}
	
	
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectedCountry;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	private WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath="//h1[contains(text(),'Thankyou for the order.')]")
	private WebElement message;
	
	private By selectedCountryByLocator = By.cssSelector(".ta-results");
	
	//Methods
	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(selectedCountryByLocator);
		selectedCountry.click();		
	}
	
	public ConfirmationPage SubmitOrder()
	{
		submit.click();
		ConfirmationPage cp = new ConfirmationPage(driver);
		return cp;
	}
	
	
}
