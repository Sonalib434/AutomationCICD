package selenium.pageobjectmodel.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.pageobjectmodel.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public String url = "https://rahulshettyacademy.com/client/";
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException { 
	  // Global Properties
	  //Properties prop = new Properties(); 
	  //FileInputStream fis = new FileInputStream("C:\\Users\\sonbhatt\\eclipse-workspace\\SeleniumFramworkDesign\\src\\main\\java\\selenium\\resource"); 
	  //Dynamic path 
	  //FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\selenium\\resource");
	  //prop.load(fis);
	  
	  //String browserName = prop.getProperty("browser");
	  String browserName = "chrome";
	  
	  if (browserName.equalsIgnoreCase("chrome")) {
	  WebDriverManager.chromedriver().setup(); 
	  driver = new ChromeDriver(); 
	  } 
	  else if (browserName.equalsIgnoreCase("firefox")) {
	  WebDriverManager.firefoxdriver().setup(); 
	  driver = new FirefoxDriver(); 
	  }
	  else if (browserName.equalsIgnoreCase("edge")) {
	  WebDriverManager.edgedriver().setup(); 
	  driver = new EdgeDriver(); 
	  }
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.manage().window().maximize(); 
	  return driver; 
	  }

	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl(url);
		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void CloseApplication() {		
		  Set<String> allWindowHandles = driver.getWindowHandles();
		  
		  if(allWindowHandles.size() > 1) 
		  { driver.quit(); 
		  } else 
		  { driver.close(); 
		  }		
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"\\reports\\" +testCaseName +".png");
		FileUtils.copyFile(source,destination);
		String imgFile = System.getProperty("user.dir")+"\\reports\\" +testCaseName +".png";
		return imgFile;
		
	}
}
