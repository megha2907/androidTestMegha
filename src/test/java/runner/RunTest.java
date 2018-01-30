package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty","json:target/cucumberreports.json" }, 
		glue = "stepDefinition", 
		features = "src\\test\\resources\\TestCases", 
			tags={"@Smoke"},
			dryRun=false
		)
public class RunTest {

}
