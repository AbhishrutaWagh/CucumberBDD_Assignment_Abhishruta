package CucumberBDD_candere.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class SearchProdObjects {

	private static final Logger logger = LogManager.getLogger(SearchProdObjects.class); 

	//------------------ Declare a WebDriver,Scenario and WebDriverWait objects --------------------//

	private WebDriver driver;
	private Scenario scn;  
	private WebDriverWait wait;

	//----------------------------- Constructor -------------------------------------//

	public SearchProdObjects(WebDriver driver,Scenario scn,WebDriverWait wait)
	{
		this.driver = driver;
		this.scn =scn;
		this.wait=wait;
	}

	//----------------------------- Locators for WebElements ------------------------------//

	private By searchBox =By.xpath("//input[@id='search']");
	private By actualProdName=By.xpath("//div[text()='Majestic Solitaire Diamond Ring']");

	//--------------------------- Methods --------------------------------------------------//

	public void userSearchProd(String productName)
	{
		WebElement searchBoxEle = driver.findElement(searchBox);
		searchBoxEle.sendKeys(productName);

		logger.info("Search result is displayed");
	}

	public void searchResultValidate(String expectedProdName) 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Majestic Solitaire Diamond Ring']")));
		WebElement actualProdNameEle = driver.findElement(actualProdName);
		System.out.println("The actual product name is "+actualProdNameEle.getText());
		Assert.assertEquals("After search product is not display same product name",expectedProdName,actualProdNameEle.getText());

		logger.info("Assertion passed for validation of search result of prduct");

	}

	public void clickOnProd() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement actualProdNameEle = driver.findElement(actualProdName);
		wait.until(ExpectedConditions.elementToBeClickable(actualProdNameEle));
		actualProdNameEle.click();

		logger.info("Successfully click on product");
	}

















}
