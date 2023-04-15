package CucumberBDD_candere.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import CucumberBDD_candere.utility.JavaScriptUtility;
import io.cucumber.java.Scenario;

public class ProductDescriptionPageObjects {

	private static final Logger logger = LogManager.getLogger(ProductDescriptionPageObjects.class); 

	//------------------ Declare a WebDriver,Scenario,WebDriverWait and JavaScriptUtility objects --------------------//

	private WebDriver driver;
	private Scenario scn;  
	private WebDriverWait wait;
	JavaScriptUtility javaUtilObject;

	//----------------------------- Constructor -------------------------------------//

	public ProductDescriptionPageObjects(WebDriver driver,WebDriverWait wait,Scenario scn)
	{
		this.driver = driver;
		this.scn =scn;
		this.wait=wait;
	}

	//----------------------------------Locators for WebElements------------------------------//

	public By ringSizeDrpDown=By.xpath("//select[@id='mt_size']");
	private By priceUpdatedEle=By.xpath("//div[text()='Price updated']");

	//---------------------------------Methods ------------------------------------------//

	public void prodDescriptionPageTitle(String expextedCurrentPageTitle)
	{
		wait.until(ExpectedConditions.titleContains(expextedCurrentPageTitle));
		String actualCurrentPageTitle = driver.getTitle();
		Assert.assertEquals("The current page title is not matching",expextedCurrentPageTitle,actualCurrentPageTitle);

		logger.info("Assertion passed for validation of current page title");
	}

	public void toSelectRingSize() 
	{
		WebElement ringSizeDrpDownEle = driver.findElement(ringSizeDrpDown);
		javaUtilObject=new JavaScriptUtility(driver);
		javaUtilObject.scrollToView(ringSizeDrpDownEle);
		Select sizeDrpDown = new Select(ringSizeDrpDownEle);
		sizeDrpDown.selectByVisibleText("14");

		logger.info("Selection of ring size get done");
	
	}

	public void validateNotificationText(String expectedNotificationText) throws InterruptedException
	{
		WebElement actualNotificationTextEle = driver.findElement(priceUpdatedEle);
		wait.until(ExpectedConditions.visibilityOf(actualNotificationTextEle));
		logger.info("Wait for element");

		System.out.println("Actual notification text is "+actualNotificationTextEle.getText());
		Assert.assertEquals("The notifiaction text is not matching", expectedNotificationText,actualNotificationTextEle.getText());

		logger.info("Assertion passed for validation of notification text");

	}



}
