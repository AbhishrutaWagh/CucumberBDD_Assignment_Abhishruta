package CucumberBDD_candere.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;



public class LandingPageObjects {

	private static final Logger logger = LogManager.getLogger(LandingPageObjects.class); 

	//------------------ Declare a WebDriver,Scenario and WebDriverWait objects --------------------//

	private WebDriver driver;
	private Scenario scn;  
	private WebDriverWait wait;

	//-----------------------------Constructor-------------------------------------//

	public LandingPageObjects(WebDriver driver,Scenario scn,WebDriverWait wait)
	{
		this.driver = driver;
		this.scn =scn;
		this.wait=wait;
	}

	//--------------------------------------- Methods -------------------------------//

	public void userIsOnLandingPage()
	{
		logger.info("User is on landing page after navigating to base URL");
	}

	public void validatePageURL(String expectedUrl)
	{
		wait.until(ExpectedConditions.urlToBe(expectedUrl));
		Assert.assertEquals("The base URL is not matching",expectedUrl,driver.getCurrentUrl());

		logger.info("validate current URL of page ,URL is: "+ driver.getCurrentUrl());

	}

	public void validateTitleOfPage(String expectedTitle)
	{
		String actualTitle = driver.getTitle();
		Assert.assertEquals("Landing page title is not matching", expectedTitle, actualTitle);

		logger.info("User is on landing page with expected landing page title");
	}








}
