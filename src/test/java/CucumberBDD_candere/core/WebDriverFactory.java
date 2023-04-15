package CucumberBDD_candere.core;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class); //logger line
	private static WebDriver driver=null;

	static int implicit_wait_timeout_in_sec=20;

	public static WebDriver getWebDriverForBrowser(String browser) throws Exception
	{
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();    
			driver=new ChromeDriver();
			logger.info("chrome browser invoked");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();               
			driver=new FirefoxDriver();
			logger.info("firefox browser invoked");
			break;
		case "opera":
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
			logger.info("opera browser invoked");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			logger.info("edge browser invoked");
			break;
		case "headless":
			ChromeOptions options=new ChromeOptions();
			options.addArguments("headless");
			driver=new ChromeDriver(options);
			logger.info("Headless chrome browser invoked");
			break;
		default:
			logger.info("no such browser implement.Browser name sent: "+browser);
			throw new Exception("no such browser implement.Browser name sent: "+browser);
		}

		logger.info("Browser got set"); 
		driver.manage().window().maximize();
		logger.info("Browser got maximized");
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
		logger.info("Browser implicite timeout set to "+implicit_wait_timeout_in_sec);
		return driver;

	}

	public static void navigateToURL(String url)
	{
		driver.get(url);
		logger.info("Browser got invoked with URL as->" +url);
	}

	public static void driverQuit()
	{
		driver.quit();
		logger.info("Browser got closed");
	}

	public static String getBrowserName()
	{
		String browserDefault = "chrome";
		String browserSentFromCmd = System.getProperty("browser");

		if(browserSentFromCmd==null)
		{
			return browserDefault;
		}
		else
		{
			return browserSentFromCmd;
		}
	}

}
