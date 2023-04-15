package CucumberBDD_candere.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="classpath:Features",  
		glue="CucumberBDD_candere.stepDef",  
		tags="",
		plugin = {"pretty",   
	            "html:target/html/htmlreport.html", 
	                                                
	            "json:target/json/file.json",
	            },
		     monochrome =true, 
		     publish=true, 
		    dryRun=false 
		    
		)

public class TestRunner {

}
