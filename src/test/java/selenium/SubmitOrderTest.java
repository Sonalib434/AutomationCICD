package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		String productName ="ZARA COAT 3";
		
		//login
		driver.findElement(By.id("userEmail")).sendKeys("contact.sonali@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("sonaliDemo123");
		driver.findElement(By.id("login")).click();

		
		//Product Catalogue
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector("button:last-of-type")).click();

		// wait until the cart added successfully message shown		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// wait until loading icon disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='cart']")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		// checking products added in cart
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equals(productName));
		Assert.assertTrue(match);
		
		//click on checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//select country from auto-suggestive drop-down
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		 /* List<WebElement> countryList=
		 * driver.findElements(By.cssSelector(".ta-results")); for(WebElement country :
		 * countryList) { if(country.getText().equalsIgnoreCase("India")) {
		 * country.click(); break; } }
		 */
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		String message = driver.findElement(By.xpath("//h1[contains(text(),'Thankyou for the order.')]")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	

			
	}

}
