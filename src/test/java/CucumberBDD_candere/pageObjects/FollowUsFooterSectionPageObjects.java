package CucumberBDD_candere.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CucumberBDD_candere.utility.JavaScriptUtility;
import io.cucumber.java.Scenario;

public class FollowUsFooterSectionPageObjects {

	private static final Logger logger = LogManager.getLogger(FollowUsFooterSectionPageObjects.class); 

	//------------------ Declare a WebDriver,Scenario,WebDriverWait and JavaScriptUtility objects --------------------//

	private WebDriver driver;
	private Scenario scn;  
	private WebDriverWait wait;
	JavaScriptUtility javaUtilObject;
	String socialMediaName;


	//----------------------------- Constructor -------------------------------------//

	public FollowUsFooterSectionPageObjects(WebDriver driver,Scenario scn,WebDriverWait wait)
	{
		this.driver = driver;
		this.scn =scn;
		this.wait=wait;
	}

	//---------------------------------- Locators for WebElements ------------------------------//

	private By followUs=By.xpath("//p[text()='FOLLOW US']");
	private By socialMedia=By.xpath("//p[text()='FOLLOW US']//following-sibling::div/a[@data-gtm='"+socialMediaName+"']");

	//-------------------------------------- Methods -------------------------------------------//

	public void scrollToViewFpllowUs()
	{
		WebElement followUsEle = driver.findElement(followUs);
		javaUtilObject=new JavaScriptUtility(driver);
		javaUtilObject.scrollToView(followUsEle);

		logger.info("User is successfully scroll down to bottom of landing page and on Follow Us section");
	}

	public void clickOnSocialMedia(String socialMediaName)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='FOLLOW US']//following-sibling::div/a[@data-gtm='"+socialMediaName+"']")));
		WebElement socialMediaEle = driver.findElement(By.xpath("//p[text()='FOLLOW US']//following-sibling::div/a[@data-gtm='"+socialMediaName+"']"));

		wait.until(ExpectedConditions.elementToBeClickable(socialMediaEle));
		socialMediaEle.click();

		logger.info("Successfully click on "+socialMediaName);
	}

	public void URLvalidation(String expectedURLContain)
	{
		wait.until(ExpectedConditions.urlContains(expectedURLContain));
		String acualUrl = driver.getCurrentUrl();
		Assert.assertEquals(true,acualUrl.contains(expectedURLContain));
		logger.info("validate current URL of page,URL is: "+ driver.getCurrentUrl());

	}

	public void textValidation(String expectedText, String socialMediaName)
	{
		if(socialMediaName.equals("facebook"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Candere by Kalyan Jewellers']")));
			WebElement facebkText = driver.findElement(By.xpath("//h1[text()='Candere by Kalyan Jewellers']"));
			String acualFacebkText = facebkText.getText().trim();
			Assert.assertEquals("The text of facebook page is not matching", expectedText, acualFacebkText);
			scn.log("Validate text of facebook page ,text is:"+acualFacebkText);

			logger.info("Validate text of facebook page ,text is:"+acualFacebkText);
		}
		else if(socialMediaName.equals("instagram"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='canderejewellery']")));
			WebElement instaText = driver.findElement(By.xpath("//h2[text()='canderejewellery']"));
			String acualInstaText = instaText.getText();
			Assert.assertEquals("The text of instagram page is not matching", expectedText, acualInstaText);
			scn.log("Validate text of instagram page ,text is:"+acualInstaText);

			logger.info("Validate text of instagram page ,text is:"+acualInstaText);
		}
		else if(socialMediaName.equals("twitter"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='UserName']//child::span[text()='Candere By Kalyan Jewellers']")));
			WebElement twiteerText = driver.findElement(By.xpath("//div[@data-testid='UserName']//child::span[text()='Candere By Kalyan Jewellers']"));
			String acualTwitText = twiteerText.getText();
			Assert.assertEquals("The text of twitter page is not matching", expectedText, acualTwitText);
			scn.log("Validate text of twitter page ,text is:"+acualTwitText);

			logger.info("Validate text of twitter page ,text is:"+acualTwitText);
		}
		else
		{
			logger.info("No such social media found");
		}


	}




}
