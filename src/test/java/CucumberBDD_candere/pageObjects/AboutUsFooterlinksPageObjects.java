package CucumberBDD_candere.pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import CucumberBDD_candere.utility.JavaScriptUtility;
import io.cucumber.java.Scenario;

public class AboutUsFooterlinksPageObjects {

	private static final Logger logger = LogManager.getLogger(AboutUsFooterlinksPageObjects.class); 

	//------------------ Declare a WebDriver,Scenario,WebDriverWait and JavaScriptUtility objects --------------------//

	private WebDriver driver;
	private Scenario scn;  
	private WebDriverWait wait;
	JavaScriptUtility javaUtilObject;	

	//----------------------------- Constructor -------------------------------------//

	public AboutUsFooterlinksPageObjects(WebDriver driver,Scenario scn,WebDriverWait wait)
	{
		this.driver = driver;
		this.scn =scn;
		this.wait=wait;

	}

	//----------------------------- Locators for WebElements ------------------------------//

	private By listOfAboutUsOptions=By.xpath("//p[text()='ABOUT US']//following-sibling::a[text()]");
	private By aboutUs=By.xpath("//p[text()='ABOUT US']");

	//---------------------------------------- Methods -------------------------------------//

	public void scrollToViewAboutUs()
	{
		WebElement aboutUsEle = driver.findElement(aboutUs);
		javaUtilObject=new JavaScriptUtility(driver);
		javaUtilObject.scrollToView(aboutUsEle);

		logger.info("User is successfully scroll down to bottom of landing page and on About Section");
	}

	public void countOfAboutUsOptions(int expectedCountOfOptions)
	{
		List<WebElement> listOfAboutUsOptionsEle = driver.findElements(listOfAboutUsOptions);
		int actualCountOfOptions =listOfAboutUsOptionsEle.size();

		logger.info("The total number of About us category's options in form of links are:"+actualCountOfOptions);

		Assert.assertEquals(expectedCountOfOptions, actualCountOfOptions);
		logger.info("Assertion get pass");
	}

	public void aboutUsOptions(List<String> expectedAboutUsOptions)
	{
		List<WebElement> listOfAboutUsOptionsEle = driver.findElements(listOfAboutUsOptions);
		for(int i=0;i<listOfAboutUsOptionsEle.size();i++)
		{
			if(expectedAboutUsOptions.get(i).equals(listOfAboutUsOptionsEle.get(i).getText()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}
		}
	}















}
