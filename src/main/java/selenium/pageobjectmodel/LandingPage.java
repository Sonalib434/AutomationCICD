package selenium.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.reusableabstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	ProductCatalogue productCatelogue;
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver,this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//PageFactory	
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement submit;
	
	@FindBy(css="[class*='overlay-container']")
	private WebElement errorMsg;
	
	
	
	//Methods
	public ProductCatalogue loginApplication(String email, String pass)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		submit.click();
		productCatelogue = new ProductCatalogue(driver);
		return productCatelogue;
	}
	
	public void goToUrl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMsg);
		String msg = errorMsg.getText();
		return msg;
	}
}
