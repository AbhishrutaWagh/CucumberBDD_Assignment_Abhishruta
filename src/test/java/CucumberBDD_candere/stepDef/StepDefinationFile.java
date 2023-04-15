package CucumberBDD_candere.stepDef;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import CucumberBDD_candere.core.WebDriverFactory;
import CucumberBDD_candere.pageObjects.AboutUsFooterlinksPageObjects;
import CucumberBDD_candere.pageObjects.FollowUsFooterSectionPageObjects;
import CucumberBDD_candere.pageObjects.LandingPageObjects;
import CucumberBDD_candere.pageObjects.ProductDescriptionPageObjects;
import CucumberBDD_candere.pageObjects.SearchProdObjects;
import CucumberBDD_candere.utility.JavaScriptUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;



public class StepDefinationFile {

	private static final Logger logger = LogManager.getLogger(StepDefinationFile.class);  

	Scenario scn;  
	WebDriver driver;
	JavaScriptUtility javaUtilObject;
	
	String url="https://www.candere.com/";

	WebDriverWait wait ;
	int webDriver_wait_in_sec=30;
	
	String socialMediaName;
	
	//---------------------Declaration of object referance of pageObject classes --------------------//

	LandingPageObjects landingPageObjects;
	SearchProdObjects searchProdObjects;
	ProductDescriptionPageObjects productDescriptionPageObjects;
	AboutUsFooterlinksPageObjects aboutUsFooterlinksPageObjects;
	FollowUsFooterSectionPageObjects followUsFooterSectionPageObjects;


	//===================================== Before Hook ===============================================================

	@Before    
	public void setUps(Scenario scn) throws Exception   
	{
		this.scn=scn;        
		String drivername = WebDriverFactory.getBrowserName() ;
		driver= WebDriverFactory.getWebDriverForBrowser(drivername);      
		scn.log("Browser got invoke");   
		wait = new WebDriverWait(driver,webDriver_wait_in_sec);

		//initialized object of pageObject classes
		landingPageObjects =new LandingPageObjects(driver,scn,wait);
		searchProdObjects=new SearchProdObjects(driver,scn,wait);
		productDescriptionPageObjects=new ProductDescriptionPageObjects(driver,wait,scn);
		aboutUsFooterlinksPageObjects=new AboutUsFooterlinksPageObjects(driver,scn,wait);
		followUsFooterSectionPageObjects=new FollowUsFooterSectionPageObjects(driver,scn,wait);


	}

	//===================================== After Hook ======================================================================

	@After(order=1)  
	public void tearDown()
	{
		WebDriverFactory.driverQuit();
		scn.log("Browser got closed");
	}

	@After(order=2)  
	public void screenshotForFailure(Scenario scn) 
	{
		if(scn.isFailed())
		{
			TakesScreenshot scrnShot=(TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "failed step name: "+scn.getName());
		}
		else
		{
			scn.log("test case is passed,no any screenshot captured");
		}
	}

	//------------------------------------- Background -------------------------------------//

	@Given("User navigate to url and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() 
	{
		WebDriverFactory.navigateToURL(url);
		scn.log("Navigate to base url");
	}

	//--------------------------------- validation of base URL ----------------------------//

	@When("User is on landing page")
	public void user_is_on_landing_page()
	{
		landingPageObjects.userIsOnLandingPage();
		scn.log("User is on landing page after navigating to base URL");	
	}

	@Then("Validate current URL of landing page with expected URL is {string}")
	public void validate_current_url_of_landing_page_with_expected_url_is(String expectedURL) 
	{
		landingPageObjects.validatePageURL(expectedURL);
		scn.log("validate current URL of page");

	}

	//------------------------------------ Scenario 1 --------------------------------------//

	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String expectedTitle) 
	{
		landingPageObjects.validateTitleOfPage(expectedTitle);
		scn.log("User is on landing page with expected landing page title");
	}

	//------------------------------------ Scenario 2 --------------------------------------//

	@When("User search for product {string}")
	public void user_search_for_product(String prodName) 
	{
		searchProdObjects.userSearchProd(prodName);
		scn.log("Search result is displayed");
	}

	@Then("Validate search result is displayed as {string}")
	public void validate_search_result_is_displayed_as(String expectProdName) 
	{
		searchProdObjects.searchResultValidate(expectProdName);
		scn.log("Assertion passed for validation of search result of prduct"); 
	}

	//------------------------------------ Scenario 3 --------------------------------------//

	@Then("Click on {string}")
	public void click_on(String expectedProdName) throws InterruptedException 
	{
		searchProdObjects.clickOnProd();
		scn.log("Successfully click on product");	
	}

	@Then("User is able to visit to next page and after clicking expected title of the page as {string}")
	public void user_is_able_to_visit_to_next_page_and_after_clicking_expected_title_of_the_page_as(String expextedPageTitle)
	{
		productDescriptionPageObjects.prodDescriptionPageTitle(expextedPageTitle);
		scn.log("Assertion passed for validation of current page title");
	}

	@Then("User is able to select ring size from drop down")
	public void user_is_able_to_select_ring_size_from_drop_down() throws InterruptedException 
	{
		productDescriptionPageObjects.toSelectRingSize();
		scn.log("Selction of ring size get done successfully");
	}

	@Then("Validate notification text as {string}")
	public void validate_notification_text_as(String expectedNotiText) throws InterruptedException 
	{
//		productDescriptionPageObjects.validateNotificationText(expectedNotiText);
//		scn.log("Assertion passed for validation of notification text");
		
		try 
		{
			System.out.println("inside try block");
			productDescriptionPageObjects.validateNotificationText(expectedNotiText);

			scn.log("Assertion passed for validation of notification text");

		}
		catch(Exception e)
		{
			System.out.println("inside catch block");
//			productDescriptionPageObjects.toSelectRingSize();
//			scn.log("Selction of ring size get done successfully");
			
//			productDescriptionPageObjects.validateNotificationText(expectedNotiText);
//			scn.log("Assertion passed for validation of notification text");

			WebElement ringSizeDrpDownEle = driver.findElement(productDescriptionPageObjects.ringSizeDrpDown);
			javaUtilObject = new JavaScriptUtility(driver);
			javaUtilObject.scrollToView(ringSizeDrpDownEle);
			Select sizeDrpDown = new Select(ringSizeDrpDownEle);
			sizeDrpDown.selectByVisibleText("16");

			logger.info("Selection of ring size get done");

			productDescriptionPageObjects.validateNotificationText(expectedNotiText);
			scn.log("Assertion passed for validation of notification text");		
		}
	}

	//------------------------------------ Scenario 4 -------------------------------------//

	@When("User scroll down to bottom of landing page until About Us visible")
	public void user_scroll_down_to_bottom_of_landing_page_until_About_Us_visible()
	{
		aboutUsFooterlinksPageObjects.scrollToViewAboutUs();
		scn.log("User is successfully scroll down to bottom of landing page and on About Section");
	}

	@When("User is able to see {int} options of About Us category")
	public void user_is_able_to_see_options_of_about_us_category(int expectedcount)
	{
		aboutUsFooterlinksPageObjects.countOfAboutUsOptions(expectedcount);
		scn.log("Assertion get pass");
	}

	@Then("Under About Us category below options are visible")
	public void under_about_us_category_below_options_are_visible(List<String> expectedAboutUsOptions)
	{
		aboutUsFooterlinksPageObjects.aboutUsOptions(expectedAboutUsOptions);

	}

	//------------------------------------ Scenario 5 -------------------------------------//

	@When("User scroll down to bottom of landing page until FOLLOW US visible")
	public void user_scroll_down_to_bottom_of_landing_page_until_follow_us_visible() 
	{

		followUsFooterSectionPageObjects.scrollToViewFpllowUs();
		scn.log("User is successfully scroll down to bottom of landing page and on Follow Us section");

	}

	@When("User click on {string}")
	public void user_click_on(String MediaName) 
	{
		followUsFooterSectionPageObjects.clickOnSocialMedia(MediaName);
		scn.log("Successfully click on "+MediaName);

	}

	@Then("Browser opened with {string}")
	public void browser_opened_with(String socialMediaName)
	{
		scn.log("Successfully browser get opened with "+socialMediaName);
		logger.info("Successfully browser get opened with "+socialMediaName);

	}

	@Then("URL should contain {string}")
	public void url_should_contain(String expectedURL) 
	{
		followUsFooterSectionPageObjects.URLvalidation(expectedURL);
		scn.log("validate current URL of page,URL is: "+ driver.getCurrentUrl());

	}

	@Then("User able to see text as {string} on perticular {string} page")
	public void user_able_to_see_text_as_on_perticular_page(String expectedText, String socialMediaName) 
	{
		followUsFooterSectionPageObjects.textValidation(expectedText, socialMediaName);
		scn.log("Successfully validate text of page");

	}

}










