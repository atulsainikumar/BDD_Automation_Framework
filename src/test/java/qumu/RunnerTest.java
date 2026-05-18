package qumu;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(

        features = "src/test/java/features",

        glue = {"qumu"},

        tags = "@UI or @API",

        monochrome = true,

        plugin = {

        		        "pretty",

        		        "html:test-output/cucumber-report.html",

        		        "json:test-output/cucumber.json",

        		        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        		}

)

@Listeners(qumu.RetryListener.class)

public class RunnerTest extends AbstractTestNGCucumberTests {

}