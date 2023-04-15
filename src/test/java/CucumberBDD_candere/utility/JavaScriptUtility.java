package CucumberBDD_candere.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility {

	WebDriver driver;
	JavascriptExecutor js;	

	//------------------------------- Constructor ---------------------------------------------//
	public JavaScriptUtility(WebDriver driver) 
	{
		this.driver = driver;
	}

	//------------------------ Methods ----------------------------//

	public void scrollToView(WebElement element)
	{
		js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}


}
